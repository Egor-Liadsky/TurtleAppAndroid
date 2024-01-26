package com.turtleteam.api.data.repository

import com.turtleteam.api.models.Institution

interface SettingsRepository {
    suspend fun getInstitutions(): List<Institution>
}