package com.turtleteam.turtleapp.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.lyadsky.analytics.AnalyticsService
import com.turtleteam.api.config.ConfigService
import com.turtleteam.api.network.BaseRepository
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.impl.config.ConfigServiceImpl
import com.turtleteam.impl.network.BaseRepositoryImpl
import com.turtleteam.storage.InstitutionDataStore
import com.turtleteam.storage.InstitutionDataStoreImpl
import com.turtleteam.storage.Storage
import com.turtleteam.storage.StorageImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val commonModule = module {
    single {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }
    single {
        HttpClient(OkHttp) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Ktor: $message")
                    }
                }
                level = LogLevel.ALL
            }
        }
    }

    // Services
    single { ErrorService() }
    single { AnalyticsService() }

    // Storage
    single<Storage> { StorageImpl(get()) }
    single<InstitutionDataStore> { InstitutionDataStoreImpl(get()) }

    // Repository
    single<BaseRepository> { BaseRepositoryImpl(get()) }

    // Config
    single<ConfigService> { ConfigServiceImpl() }
}
