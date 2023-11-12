package com.turtleteam.core_view.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    val days: List<Day>,
    val name: String,
)

@Serializable
data class Day(
    @SerialName("apairs")
    val pairs: List<Pairs>,
    val isoDateDay: String,
    val day: String,
)

@Serializable
data class Pairs(
    val time: String,
    @SerialName("apair")
    val pair: List<Pair>,
    val isoDateStart: String,
    val isoDateEnd: String,
)

@Serializable
data class Pair(
    val doctrine: String,
    val teacher: String,
    val auditoria: String,
    val corpus: String,
    val number: Int,
    val start: String,
    val end: String,
    val warn: String,
)
