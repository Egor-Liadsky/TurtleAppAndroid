package com.turtleteam.core_data.error

data class ServerException(
    val errorCode: Int,
    val errorMessage: String
) : Exception(errorMessage)
