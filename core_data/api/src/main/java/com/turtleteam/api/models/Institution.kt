package com.turtleteam.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Institution(
    val id: Int? = null,
    val title: String? = null,
    val code: String? = null,
    val port: Int? = null
)
