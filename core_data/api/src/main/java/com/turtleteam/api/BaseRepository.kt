package com.turtleteam.api

import io.ktor.http.HttpMethod

interface BaseRepository {

    suspend fun executeCall(
        type: HttpMethod,
        path: String,
        parameters: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        body: String? = null,
    ): String
}