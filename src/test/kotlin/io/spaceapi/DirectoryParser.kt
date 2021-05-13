/**
 * spaceapi-kt
 * https://github.com/spaceapi-community/spaceapi-kt/
 * Copyright (C) 2020-2021 Danilo Bargen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package io.spaceapi

import com.github.kittinunf.fuel.core.requests.CancellableRequest
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.*
import kotlin.collections.HashMap

// A command line program to parse all endpoints in the directory.

const val ESC = "\u001B"
const val RESET = "$ESC[0m"
const val RED = "$ESC[0;31m"
const val GREEN = "$ESC[0;32m"

data class ProjectInfo(val name: String, val version: String)

fun getProjectInfo(): ProjectInfo {
    val loader = Thread.currentThread().contextClassLoader
    val props = Properties()
    loader.getResourceAsStream("project.properties").use { resourceStream -> props.load(resourceStream) }
    return ProjectInfo(props.getProperty("projectName")!!, props.getProperty("projectVersion")!!)
}

class DirectoryParser(directoryUrl: String) {
    private val spaceEndpoints: Map<String, String>
    private var spaceEndpointData: MutableMap<String, String> = HashMap()
    private val userAgent: String

    init {
        // Determine user agent
        val projectInfo = getProjectInfo()
        userAgent = "${projectInfo.name}/${projectInfo.version}"

        // Request directory
        val result = directoryUrl
                .httpGet()
                .header("user-agent", userAgent)
                .responseString()
                .third
        val data = when (result) {
            is Result.Failure -> {
                val ex = result.getException()
                println("${RED}Could not fetch SpaceAPI directory:${RESET}")
                throw ex
            }
            is Result.Success -> result.get()
        }
        spaceEndpoints = Json.decodeFromString(data)
    }

    fun spaceCount(): Int = spaceEndpoints.size

    /**
     * Download all endpoints in `spaceEndpoints` and put the resulting bodies
     * into the `spaceEndpointData` map.
     */
    fun downloadEndpoints() {
        println("--> Sending requests")
        val requests: List<Pair<String, CancellableRequest>> = spaceEndpoints.map { entry ->
                val space = entry.key
                val url = entry.value
                val req = url.httpGet().header("user-agent", userAgent).header("accept", "application/json")
                // Set request and read timeout to 10s
                req.executionOptions.timeoutInMillisecond = 10_000
                req.executionOptions.timeoutReadInMillisecond = 10_000
                val cancelableReq = req.responseString { result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            println("${RED}--> Fetching $space failed: ${ex.message}${RESET}")
                        }
                        is Result.Success -> {
                            spaceEndpointData[space] = result.get()
                        }
                    }
                }
                Pair(space, cancelableReq)
        }
        println("--> Collecting responses")
        val requestCount = requests.size
        requests.forEachIndexed { i, (space, request) ->
            println("--> Waiting for $space (${i + 1}/$requestCount)...")
            request.join()
        }
    }

    /**
     * Parse all endpoints in `spaceEndpointsData`, print the success state.
     */
    fun parseEndpoints() {
        for ((space, data) in spaceEndpointData) {
            try {
                parseString(data)
                println("${GREEN}--> $space OK${RESET}")
            } catch (ex: Exception) {
                println("${RED}--> $space FAILED: ${ex.message}${RESET}")
            }
        }
    }
}

fun main() {
    println("==> Downloading directory...")
    val parser = DirectoryParser("https://directory.spaceapi.io/")
    println("${GREEN}--> Found ${parser.spaceCount()} spaces!${RESET}")
    println("==> Downloading all spaces asynchronously...")
    parser.downloadEndpoints()
    println("==> Deserializing all downloaded endpoints...")
    parser.parseEndpoints()
}
