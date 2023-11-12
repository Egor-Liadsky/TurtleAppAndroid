package com.turtleteam.core_view.model

import kotlinx.serialization.Serializable

@Serializable
data class GroupAndTeacher(
    val group: List<String>? = null,
    val teacher: List<String>? = null
)
