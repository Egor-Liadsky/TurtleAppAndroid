package com.turtleteam.api.data.repository

import com.turtleteam.core_view.model.GroupAndTeacher
import com.turtleteam.core_view.model.Schedule

interface GroupRepository {

    suspend fun getGroups(): GroupAndTeacher

    suspend fun getSchedule(group: String): Schedule
}
