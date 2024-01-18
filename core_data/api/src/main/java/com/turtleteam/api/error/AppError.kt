package com.turtleteam.api.error

import java.lang.Error

data class AppError(
    val code: Code = Code.INTERNAL_ERROR,
    val description: String? = null
): Error(description)

enum class Code {
    CONFLICT,
    INTERNAL_ERROR,
    UNAUTHORIZED
}
