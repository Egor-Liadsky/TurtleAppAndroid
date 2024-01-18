package com.turtleteam.api.data.repository

import com.turtleteam.api.models.GroupAndTeacher
import com.turtleteam.api.models.Institution

interface WelcomeRepository {

    suspend fun getInstitutions(): List<Institution>
    suspend fun getGroups(): GroupAndTeacher
}
