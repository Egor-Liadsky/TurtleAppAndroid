package com.turtleteam.impl.data.repository

import com.turtleteam.api.BaseRepository
import com.turtleteam.api.data.repository.TeacherRepository
import com.turtleteam.api.models.GroupAndTeacher
import com.turtleteam.api.models.Schedule
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class TeacherRepositoryImpl(private val repository: BaseRepository) : TeacherRepository {

    override suspend fun getSchedule(group: String): Schedule {
        val response = repository.executeCall(
            type = HttpMethod.Get,
            path = "schedule/$group",
            headers = mapOf("Content-Type" to "application/json"),
        )
        return Json.decodeFromString(response)
    }

    override suspend fun getTeachers(): GroupAndTeacher {
        val response = repository.executeCall(
            type = HttpMethod.Get,
            path = "schedule/list",
            headers = mapOf("Content-Type" to "application/json"),
        )
        return Json.decodeFromString(response)
    }
}
