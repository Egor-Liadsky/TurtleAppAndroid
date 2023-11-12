package com.turtleteam.api.data.repository

import com.turtleteam.core_view.model.GroupAndTeacher
import com.turtleteam.core_view.model.Institution

interface WelcomeRepository {

    suspend fun getInstitutions(): List<Institution>
    suspend fun getGroups(): GroupAndTeacher
}
