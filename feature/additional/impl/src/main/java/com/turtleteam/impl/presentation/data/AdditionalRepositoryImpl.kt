package com.turtleteam.impl.presentation.data

import com.turtleteam.api.data.repository.AdditionalRepository
import com.turtleteam.core_data.BaseRepository
import com.turtleteam.core_view.model.Ring
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class AdditionalRepositoryImpl(httpClient: HttpClient) : AdditionalRepository,
    BaseRepository(httpClient) {

    override suspend fun getRings(): Ring {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "ring",
            headers = mapOf("Content-Type" to "application/json"),
        )
        return Json.decodeFromString(response)
    }
}