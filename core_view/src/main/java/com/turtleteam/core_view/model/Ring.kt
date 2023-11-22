package com.turtleteam.core_view.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ring(
    @SerialName("ring_name")
    val ringName: List<RingName>? = null,
    val value: List<Value>? = null
)

@Serializable
data class RingName(
    val name: String? = null,
    val value: String? = null
)

@Serializable
data class Value(
    val type: String? = null,
    val rings: List<RingInfo>? = null
)

@Serializable
data class RingInfo(
    val number: Int? = null,
    val from: String? = null,
    val to: String? = null,
    val name: String? = null
)

