package com.turtleteam.api.data.repository

import com.turtleteam.api.models.Ring

interface AdditionalRepository {

    suspend fun getRings(): Ring
}