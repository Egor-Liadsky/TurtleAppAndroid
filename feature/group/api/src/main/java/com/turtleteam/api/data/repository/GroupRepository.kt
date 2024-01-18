package com.turtleteam.api.data.repository

import com.turtleteam.api.models.GroupAndTeacher
import com.turtleteam.api.models.Schedule

interface GroupRepository {

    suspend fun getGroups(): GroupAndTeacher

    suspend fun getSchedule(group: String): Schedule
}
