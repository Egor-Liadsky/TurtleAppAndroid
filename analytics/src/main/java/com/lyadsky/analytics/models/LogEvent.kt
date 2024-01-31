package com.lyadsky.analytics.models

import com.google.firebase.analytics.FirebaseAnalytics

enum class LogEvent (val value: String) {

    OPEN_SCREEN(FirebaseAnalytics.Event.SCREEN_VIEW),
}

enum class LogEventParam(val value: String) {
    SCREEN_NAME(FirebaseAnalytics.Param.SCREEN_NAME),
    SELECTED_GROUP("selected_group"),
    ERROR_MESSAGE("error_message")
}