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
    var temperature: Array<Temperature> = emptyArray(),
    var door_locked: Array<DoorLocked> = emptyArray(),
    var barometer: Array<Barometer> = emptyArray(),
    var radiation: Radiation? = null,
    var humidity: Array<Humidity> = emptyArray(),
    var beverage_supply: Array<BeverageSupply> = emptyArray(),
    var power_consumption: Array<PowerConsumption> = emptyArray(),
    var wind: Array<Wind> = emptyArray(),
    var network_connections: Array<NetworkConnection> = emptyArray(),
    var account_balance: Array<AccountBalance> = emptyArray(),
    var total_member_count: Array<MemberCount> = emptyArray(),
    var people_now_present: Array<PeoplePresent> = emptyArray(),
)

@Serializable
data class Temperature(
    var value: Float,
    var unit: String,
    var location: String,
    var name: String? = null,
    var description: String? = null,
)

@Serializable
data class DoorLocked(
    var value: Boolean,
    var location: String,
    var name: String? = null,
    var description: String? = null,
)

@Serializable
data class Barometer(
    var value: Float,
    var unit: String,
    var location: String,
    var name: String? = null,
    var description: String? = null,
)

@Serializable
data class Radiation(
    var alpha: Array<RadiationSensor> = emptyArray(),
    var beta: Array<RadiationSensor> = emptyArray(),
    var gamma: Array<RadiationSensor> = emptyArray(),
    var beta_gamma: Array<RadiationSensor> = emptyArray(),
)

@Serializable
data class RadiationSensor(
    var value: Float,
    var unit: String,
    var dead_time: Float? = null,
    var conversion_factor: Float? = null,
    var location: String? = null,
    var name: String? = null,
    var description: String? = null,
)

@Serializable
data class Humidity(
    var value: Float,
    var unit: String,
    var location: String,
    var name: String? = null,
    var description: String? = null,
)

@Serializable
data class BeverageSupply(
    var value: Float,
    var unit: String,
    var location: String? = null,
    var name: String? = null,
    var description: String? = null,
)

@Serializable
data class PowerConsumption(
    var value: Float,
    var unit: String,
    var location: String,
    var name: String? = null,
    var description: String? = null,
)

@Serializable
data class Wind(
    var properties: WindProperties,
    var location: String,
    var name: String? = null,
    var description: String? = null,
)

@Serializable
data class WindProperties(
    var speed: WindSpeed,
    var gust: WindSpeed,
    var direction: WindDirection,
    var elevation: Elevation,
)

@Serializable
data class WindSpeed(
    var value: Float,
    var unit: String,
)

@Serializable
data class WindDirection(
    var value: Float,
    var unit: String,
)

@Serializable
data class Elevation(
    var value: Float,
    var unit: String,
)

@Serializable
data class NetworkConnection(
    var type: String? = null,
    var value: Long,
    var location: String? = null,
    var machines: Array<Machine> = emptyArray(),
    var name: String? = null,
    var description: String? = null,
)

@Serializable
data class Machine(
    var name: String? = null,
    var mac: String,
)

@Serializable
data class AccountBalance(
    var value: Float,
    var unit: String,
    var location: String? = null,
    var name: String? = null,
    var description: String? = null,
)

@Serializable
data class MemberCount(
    var value: Long,
    var location: String? = null,
    var name: String? = null,
    var description: String? = null,
)

@Serializable
data class PeoplePresent(
    var value: Long,
    var location: String? = null,
    var name: String? = null,
    var names: Array<String> = emptyArray(),
    var description: String? = null,
)
