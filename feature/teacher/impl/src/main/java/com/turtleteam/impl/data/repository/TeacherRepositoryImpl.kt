package com.turtleteam.impl.data.repository

import com.turtleteam.api.data.repository.TeacherRepository
import com.turtleteam.core_data.BaseRepository
import com.turtleteam.core_view.model.GroupAndTeacher
import com.turtleteam.core_view.model.Schedule
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class TeacherRepositoryImpl(httpClient: HttpClient) : TeacherRepository,
    BaseRepository(httpClient) {

    override suspend fun getSchedule(group: String): Schedule {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "schedule/$group",
            headers = mapOf("Content-Type" to "application/json"),
        )
        return Json.decodeFromString(response)
    }

    override suspend fun getTeachers(): GroupAndTeacher {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "schedule/list",
            headers = mapOf("Content-Type" to "application/json"),
        )
        return Json.decodeFromString(response)
    }
}
