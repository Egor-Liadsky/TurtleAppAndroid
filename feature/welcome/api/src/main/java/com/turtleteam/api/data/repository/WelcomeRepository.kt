package com.turtleteam.api.data.repository

import com.turtleteam.api.data.model.Institution

interface WelcomeRepository {

    suspend fun getInstitutions(): List<Institution>
}
