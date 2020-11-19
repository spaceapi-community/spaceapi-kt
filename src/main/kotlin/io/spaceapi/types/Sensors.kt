/**
 * spaceapi-kt
 * https://github.com/spaceapi-community/spaceapi-kt/
 * Copyright (C) 2020 Danilo Bargen
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
    val temperature: Array<Temperature> = emptyArray(),
    val door_locked: Array<DoorLocked> = emptyArray(),
    val barometer: Array<Barometer> = emptyArray(),
    val radiation: Radiation? = null,
    val humidity: Array<Humidity> = emptyArray(),
    val beverage_supply: Array<BeverageSupply> = emptyArray(),
    val power_consumption: Array<PowerConsumption> = emptyArray(),
    val wind: Array<Wind> = emptyArray(),
    val network_connections: Array<NetworkConnection> = emptyArray(),
    val account_balance: Array<AccountBalance> = emptyArray(),
    val total_member_count: Array<MemberCount> = emptyArray(),
    val people_now_present: Array<PeoplePresent> = emptyArray(),
)

@Serializable
data class Temperature(
    val value: Float,
    val unit: String,
    val location: String,
    val name: String? = null,
    val description: String? = null,
)

@Serializable
data class DoorLocked(
    val value: Boolean,
    val location: String,
    val name: String? = null,
    val description: String? = null,
)

@Serializable
data class Barometer(
    val value: Float,
    val unit: String,
    val location: String,
    val name: String? = null,
    val description: String? = null,
)

@Serializable
data class Radiation(
    val alpha: Array<RadiationSensor>,
    val beta: Array<RadiationSensor>,
    val gamma: Array<RadiationSensor>,
    val beta_gamma: Array<RadiationSensor>,
)

@Serializable
data class RadiationSensor(
    val value: Float,
    val unit: String,
    val dead_time: Float? = null,
    val conversion_factor: Float? = null,
    val location: String,
    val name: String? = null,
    val description: String? = null,
)

@Serializable
data class Humidity(
    val value: Float,
    val unit: String,
    val location: String,
    val name: String? = null,
    val description: String? = null,
)

@Serializable
data class BeverageSupply(
    val value: Float,
    val unit: String,
    val location: String,
    val name: String? = null,
    val description: String? = null,
)

@Serializable
data class PowerConsumption(
    val value: Float,
    val unit: String,
    val location: String,
    val name: String? = null,
    val description: String? = null,
)

@Serializable
data class Wind(
    val properties: WindProperties,
    val location: String,
    val name: String? = null,
    val description: String? = null,
)

@Serializable
data class WindProperties(
    val speed: WindSpeed,
    val gust: WindSpeed,
    val direction: WindDirection,
    val elevation: Elevation,
)

@Serializable
data class WindSpeed(
    val value: Float,
    val unit: String,
)

@Serializable
data class WindDirection(
    val value: Float,
    val unit: String,
)

@Serializable
data class Elevation(
    val value: Float,
    val unit: String,
)

@Serializable
data class NetworkConnection(
    val type: String? = null,
    val value: Long,
    val location: String? = null,
    val machines: Array<Machine> = emptyArray(),
    val name: String? = null,
    val description: String? = null,
)

@Serializable
data class Machine(
    val name: String? = null,
    val mac: String,
)

@Serializable
data class AccountBalance(
    val value: Float,
    val unit: String,
    val location: String? = null,
    val name: String? = null,
    val description: String? = null,
)

@Serializable
data class MemberCount(
    val value: Long,
    val location: String? = null,
    val name: String? = null,
    val description: String? = null,
)

@Serializable
data class PeoplePresent(
    val value: Long,
    val location: String? = null,
    val name: String? = null,
    val names: Array<String> = emptyArray(),
    val description: String? = null,
)
