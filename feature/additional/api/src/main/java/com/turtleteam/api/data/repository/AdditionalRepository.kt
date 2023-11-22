package com.turtleteam.api.data.repository

import com.turtleteam.core_view.model.Ring

interface AdditionalRepository {

    suspend fun getRings(): Ring
}