package com.turtleteam.api.data.repository

import com.turtleteam.api.models.GroupAndTeacher
import com.turtleteam.api.models.Schedule


interface TeacherRepository {

    suspend fun getTeachers(): GroupAndTeacher

    suspend fun getSchedule(group: String): Schedule
}
