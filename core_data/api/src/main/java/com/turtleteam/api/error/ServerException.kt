package com.turtleteam.api.error

data class ServerException(
    val errorCode: Int,
    val errorMessage: String
) : Exception(errorMessage)
