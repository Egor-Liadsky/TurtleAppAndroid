package com.turtleteam.impl.network

import com.turtleteam.api.network.BaseRepository
import com.turtleteam.api.network.error.AppError
import com.turtleteam.api.network.error.Code
import com.turtleteam.api.network.error.ServerException
import com.turtleteam.storage.InstitutionDataStore
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.appendPathSegments
import io.ktor.utils.io.errors.IOException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BaseRepositoryImpl(private val httpClient: HttpClient) : KoinComponent,
    BaseRepository {

    private val institutionDataStore: InstitutionDataStore by inject()

    override suspend fun executeCall(
        type: HttpMethod,
        path: String,
        parameters: Map<String, String>?,
        headers: Map<String, String>?,
        body: String?,
    ): String {
        val port = institutionDataStore.getInstitution()?.port

        val url = "http://45.155.207.232:${port ?: "8080"}/api/v2/"
        val response: HttpResponse
        try {
            response = httpClient.request(url) {
                url {
                    appendPathSegments(path)
                    parameters?.forEach { this.parameters.append(it.key, it.value) }
                }
                method = type
                headers?.forEach { this.headers.append(it.key, it.value) }
                body?.let { setBody(body) }
            }
        } catch (e: SocketTimeoutException) {
            throw Exception(e)
        } catch (e: IOException) {
            throw Exception(e)
        }

        if (response.status.value !in 200..299) {
            throw ServerException(response.status.value, response.status.description)
        }
        if (response.status == HttpStatusCode.Conflict) {
            throw AppError(code = Code.CONFLICT, description = "Already exists")
        }
        if (response.status == HttpStatusCode.Unauthorized) {
            throw AppError(code = Code.UNAUTHORIZED, description = "User unauthorized")
        }

        return response.body()
    }
}
