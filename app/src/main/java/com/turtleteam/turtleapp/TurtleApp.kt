package com.turtleteam.turtleapp

import android.app.Application
import com.turtleteam.turtleapp.di.commonModule
import com.turtleteam.turtleapp.di.featureModule.additionalModule
import com.turtleteam.turtleapp.di.featureModule.groupModule
import com.turtleteam.turtleapp.di.featureModule.settingsModule
import com.turtleteam.turtleapp.di.featureModule.teacherModule
import com.turtleteam.turtleapp.di.featureModule.welcomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class TurtleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TurtleApp)
            androidLogger(Level.DEBUG)
            modules(
                commonModule,
                welcomeModule,
                groupModule,
                teacherModule,
                settingsModule,
                additionalModule
            )
        }
    }
}
