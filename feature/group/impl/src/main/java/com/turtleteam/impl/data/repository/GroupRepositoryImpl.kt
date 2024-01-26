package com.turtleteam.impl.data.repository

import com.turtleteam.api.network.BaseRepository
import com.turtleteam.api.data.repository.GroupRepository
import com.turtleteam.api.models.GroupAndTeacher
import com.turtleteam.api.models.Schedule
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class GroupRepositoryImpl(private val repository: BaseRepository) : GroupRepository {

    override suspend fun getSchedule(group: String): Schedule {
        val response = repository.executeCall(
            type = HttpMethod.Get,
            path = "schedule/$group",
            headers = mapOf("Content-Type" to "application/json"),
        )
        return Json.decodeFromString(response)
    }

    override suspend fun getGroups(): GroupAndTeacher {
        val response = repository.executeCall(
            type = HttpMethod.Get,
            path = "schedule/list",
            headers = mapOf("Content-Type" to "application/json"),
        )
        return Json.decodeFromString(response)
    }
}
