/**
 * spaceapi-kt
 * https://github.com/spaceapi-community/spaceapi-kt/
 * Copyright (C) 2020-2025 Danilo Bargen
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
@file:UseSerializers(RoundingLongSerializer::class)

package io.spaceapi.types

import io.spaceapi.types.serializers.RoundingLongSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
data class Sensors(
    @JvmField
    var temperature: Array<Temperature> = emptyArray(),
    @JvmField
    var door_locked: Array<DoorLocked> = emptyArray(),
    @JvmField
    var barometer: Array<Barometer> = emptyArray(),
    @JvmField
    var radiation: Radiation? = null,
    @JvmField
    var humidity: Array<Humidity> = emptyArray(),
    @JvmField
    var beverage_supply: Array<BeverageSupply> = emptyArray(),
    @JvmField
    var power_consumption: Array<PowerConsumption> = emptyArray(),
    @JvmField
    var wind: Array<Wind> = emptyArray(),
    @JvmField
    var network_connections: Array<NetworkConnection> = emptyArray(),
    @JvmField
    var account_balance: Array<AccountBalance> = emptyArray(),
    @JvmField
    var total_member_count: Array<MemberCount> = emptyArray(),
    @JvmField
    var people_now_present: Array<PeoplePresent> = emptyArray(),
    @JvmField
    var network_traffic: Array<NetworkTraffic> = emptyArray(),
    @JvmField
    var power_generation: Array<PowerGeneration> = emptyArray(),
    @JvmField
    var carbondioxide: Array<CarbonDioxide> = emptyArray(),
)

@Serializable
data class Temperature(
    @JvmField
    var value: Float,
    @JvmField
    var unit: String,
    @JvmField
    var location: String,
    @JvmField
    var name: String? = null,
    @JvmField
    var description: String? = null,
    @JvmField
    var lastchange: Float? = null,
)

@Serializable
data class DoorLocked(
    @JvmField
    var value: Boolean,
    @JvmField
    var location: String,
    @JvmField
    var name: String? = null,
    @JvmField
    var description: String? = null,
    @JvmField
    var lastchange: Float? = null,
)

@Serializable
data class Barometer(
    @JvmField
    var value: Float,
    @JvmField
    var unit: String,
    @JvmField
    var location: String,
    @JvmField
    var name: String? = null,
    @JvmField
    var description: String? = null,
    @JvmField
    var lastchange: Float? = null,
)

@Serializable
data class Radiation(
    @JvmField
    var alpha: Array<RadiationSensor> = emptyArray(),
    @JvmField
    var beta: Array<RadiationSensor> = emptyArray(),
    @JvmField
    var gamma: Array<RadiationSensor> = emptyArray(),
    @JvmField
    var beta_gamma: Array<RadiationSensor> = emptyArray(),
)

@Serializable
data class RadiationSensor(
    @JvmField
    var value: Float,
    @JvmField
    var unit: String,
    @JvmField
    var dead_time: Float? = null,
    @JvmField
    var conversion_factor: Float? = null,
    @JvmField
    var location: String? = null,
    @JvmField
    var name: String? = null,
    @JvmField
    var description: String? = null,
    @JvmField
    var lastchange: Float? = null,
)

@Serializable
data class Humidity(
    @JvmField
    var value: Float,
    @JvmField
    var unit: String,
    @JvmField
    var location: String,
    @JvmField
    var name: String? = null,
    @JvmField
    var description: String? = null,
    @JvmField
    var lastchange: Float? = null,
)

@Serializable
data class BeverageSupply(
    @JvmField
    var value: Float,
    @JvmField
    var unit: String,
    @JvmField
    var location: String? = null,
    @JvmField
    var name: String? = null,
    @JvmField
    var description: String? = null,
    @JvmField
    var lastchange: Float? = null,
)

@Serializable
data class PowerConsumption(
    @JvmField
    var value: Float,
    @JvmField
    var unit: String,
    @JvmField
    var location: String,
    @JvmField
    var name: String? = null,
    @JvmField
    var description: String? = null,
    @JvmField
    var lastchange: Float? = null,
)

@Serializable
data class Wind(
    @JvmField
    var properties: WindProperties,
    @JvmField
    var location: String,
    @JvmField
    var name: String? = null,
    @JvmField
    var description: String? = null,
    @JvmField
    var lastchange: Float? = null,
)

@Serializable
data class WindProperties(
    @JvmField
    var speed: WindSpeed,
    @JvmField
    var gust: WindSpeed,
    @JvmField
    var direction: WindDirection,
    @JvmField
    var elevation: Elevation,
)

@Serializable
data class WindSpeed(
    @JvmField
    var value: Float,
    @JvmField
    var unit: String,
)

@Serializable
data class WindDirection(
    @JvmField
    var value: Float,
    @JvmField
    var unit: String,
)

@Serializable
data class Elevation(
    @JvmField
    var value: Float,
    @JvmField
    var unit: String,
)

@Serializable
data class NetworkConnection(
    @JvmField
    var type: String? = null,
    @JvmField
    var value: Long,
    @JvmField
    var location: String? = null,
    @JvmField
    var machines: Array<Machine> = emptyArray(),
    @JvmField
    var name: String? = null,
    @JvmField
    var description: String? = null,
    @JvmField
    var lastchange: Float? = null,
)

@Serializable
data class Machine(
    @JvmField
    var name: String? = null,
    @JvmField
    var mac: String,
)

@Serializable
data class AccountBalance(
    @JvmField
    var value: Float,
    @JvmField
    var unit: String,
    @JvmField
    var location: String? = null,
    @JvmField
    var name: String? = null,
    @JvmField
    var description: String? = null,
    @JvmField
    var lastchange: Float? = null,
)

@Serializable
data class MemberCount(
    @JvmField
    var value: Long,
    @JvmField
    var location: String? = null,
    @JvmField
    var name: String? = null,
    @JvmField
    var description: String? = null,
    @JvmField
    var lastchange: Float? = null,
)

@Serializable
data class PeoplePresent(
    @JvmField
    var value: Long,
    @JvmField
    var location: String? = null,
    @JvmField
    var name: String? = null,
    @JvmField
    var names: Array<String> = emptyArray(),
    @JvmField
    var description: String? = null,
    @JvmField
    var lastchange: Float? = null,
)

@Serializable
data class NetworkTraffic(
    @JvmField
    var properties: NetworkTrafficProperties,
    @JvmField
    var name: String? = null,
    @JvmField
    var location: String? = null,
    @JvmField
    var description: String? = null,
    @JvmField
    var lastchange: Float? = null,
)

@Serializable
data class NetworkTrafficProperties(
    @JvmField
    var bits_per_second: BitsPerSecond? = null,
    @JvmField
    var packets_per_second: PacketsPerSecond? = null,
)

@Serializable
data class BitsPerSecond(
    @JvmField
    var value: Long,
    @JvmField
    var maximum: Long? = null,
)

@Serializable
data class PacketsPerSecond(
    @JvmField
    var value: Long,
)

@Serializable
data class PowerGeneration(
    @JvmField
    var value: Float,
    @JvmField
    var unit: String,
    @JvmField
    var location: String,
    @JvmField
    var name: String? = null,
    @JvmField
    var description: String? = null,
    @JvmField
    var lastchange: Float? = null,
)

@Serializable
data class CarbonDioxide(
    @JvmField
    var value: Float,
    @JvmField
    var unit: String,
    @JvmField
    var location: String,
    @JvmField
    var name: String? = null,
    @JvmField
    var description: String? = null,
    @JvmField
    var lastchange: Float? = null,
)
