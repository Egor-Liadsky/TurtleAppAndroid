package com.turtleteam.impl.data.repository

import com.turtleteam.api.data.repository.WelcomeRepository
import com.turtleteam.core_data.BaseRepository
import com.turtleteam.core_view.model.GroupAndTeacher
import com.turtleteam.core_view.model.Institution
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class WelcomeRepositoryImpl(httpClient: HttpClient) : WelcomeRepository,
    BaseRepository(httpClient) {

    override suspend fun getInstitutions(): List<Institution> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "ports",
            headers = mapOf("Content-Type" to "application/json"),
        )
        return Json.decodeFromString(response)
    }

    override suspend fun getGroups(): GroupAndTeacher {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "schedule/list",
            headers = mapOf("Content-Type" to "application/json"),
        )
        return Json.decodeFromString(response)
    }
}
