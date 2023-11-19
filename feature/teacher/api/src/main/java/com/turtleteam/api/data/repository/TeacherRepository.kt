package com.turtleteam.api.data.repository

import com.turtleteam.core_view.model.GroupAndTeacher
import com.turtleteam.core_view.model.Schedule

interface TeacherRepository {

    suspend fun getTeachers(): GroupAndTeacher

    suspend fun getSchedule(group: String): Schedule
}
