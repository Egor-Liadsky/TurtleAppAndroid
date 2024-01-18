package com.turtleteam.api.models

import kotlinx.serialization.Serializable

@Serializable
data class GroupAndTeacher(
    val group: List<String>? = null,
    val teacher: List<String>? = null
)
