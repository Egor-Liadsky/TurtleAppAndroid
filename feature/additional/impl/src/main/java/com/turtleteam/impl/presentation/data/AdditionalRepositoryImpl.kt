package com.turtleteam.impl.presentation.data

import com.turtleteam.api.network.BaseRepository
import com.turtleteam.api.data.repository.AdditionalRepository
import com.turtleteam.api.models.Ring
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class AdditionalRepositoryImpl(private val repository: BaseRepository) : AdditionalRepository{

    override suspend fun getRings(): Ring {
        val response = repository.executeCall(
            type = HttpMethod.Get,
            path = "ring",
            headers = mapOf("Content-Type" to "application/json"),
        )
        return Json.decodeFromString(response)
    }
}