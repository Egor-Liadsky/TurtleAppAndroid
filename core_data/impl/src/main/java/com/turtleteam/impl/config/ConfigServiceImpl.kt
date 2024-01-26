package com.turtleteam.impl.config

import com.example.impl.BuildConfig
import com.turtleteam.api.config.ConfigService

class ConfigServiceImpl : ConfigService {

    override fun getVersionCode(): Int = BuildConfig.versionCode
    override fun getVersionName(): String = BuildConfig.versionName
    override fun getBaseUrl(): String = BuildConfig.baseUrl
    override fun getPlanshetkaUrl(): String = BuildConfig.planshetkaUrl
    override fun getFeedbackEmail(): String = BuildConfig.feedbackEmail
}