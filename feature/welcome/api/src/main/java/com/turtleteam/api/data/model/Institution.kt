package com.turtleteam.api.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Institution(
    val id: Int,
    val title: String,
    val code: String,
    val port: Int
)
