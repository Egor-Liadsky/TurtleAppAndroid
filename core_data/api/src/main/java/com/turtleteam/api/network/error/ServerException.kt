package com.turtleteam.api.network.error

data class ServerException(
    val errorCode: Int,
    val errorMessage: String
) : Exception(errorMessage)
