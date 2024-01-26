package com.turtleteam.api.config

interface ConfigService {

    fun getVersionCode(): Int
    fun getVersionName(): String
    fun getBaseUrl(): String
    fun getPlanshetkaUrl(): String
    fun getFeedbackEmail(): String
}