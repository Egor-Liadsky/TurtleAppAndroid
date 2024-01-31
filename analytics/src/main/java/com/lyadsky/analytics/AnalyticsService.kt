package com.lyadsky.analytics

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.ktx.Firebase
import com.lyadsky.analytics.models.LogEvent
import com.lyadsky.analytics.models.LogEventParam

class AnalyticsService {

    fun sendEvent(event: LogEvent, params: Map<LogEventParam, Any>) {
        Firebase.analytics.logEvent(event.value) {
            for (item in params) {
                param(item.key.value, item.value.toString())
            }
        }
    }
}