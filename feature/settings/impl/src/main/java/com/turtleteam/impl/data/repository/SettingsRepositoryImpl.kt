package com.turtleteam.impl.data.repository

import com.turtleteam.api.network.BaseRepository
import com.turtleteam.api.data.repository.SettingsRepository
import com.turtleteam.api.models.Institution
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class SettingsRepositoryImpl(private val repository: BaseRepository): SettingsRepository {

    override suspend fun getInstitutions(): List<Institution> {
        val response = repository.executeCall(
            type = HttpMethod.Get,
            path = "ports",
            headers = mapOf("Content-Type" to "application/json"),
        )
        return Json.decodeFromString(response)
    }
}