package com.turtleteam.impl.presentation.data.repository

import com.turtleteam.api.data.model.Institution
import com.turtleteam.api.data.repository.WelcomeRepository
import com.turtleteam.core_network.BaseRepository
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class WelcomeRepositoryImpl(httpClient: HttpClient): WelcomeRepository, BaseRepository(httpClient) {

    override suspend fun getInstitutions(): List<Institution> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "ports",
            headers = mapOf("Content-Type" to "application/json",)
        )
        return Json.decodeFromString(response)
    }
}
