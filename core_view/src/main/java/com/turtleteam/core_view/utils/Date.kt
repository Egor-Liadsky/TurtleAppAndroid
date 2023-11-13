package com.turtleteam.core_view.utils

import android.icu.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun String.toDate() = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.forLanguageTag("RU")).parse(this)!!

fun String.toCalendar(): Calendar {
    val timeInMillis = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.forLanguageTag("RU")).parse(this).time
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timeInMillis
    return calendar
}

fun Calendar.toMonth(): String = SimpleDateFormat("d MMMM", Locale.forLanguageTag("RU")).format(time)

fun Int.toDayOfWeek(): String {
    return when (this) {
        2 -> "ПН"
        3 -> "ВТ"
        4 -> "СР"
        5 -> "ЧТ"
        6 -> "ПТ"
        7 -> "СБ"
        1 -> "ВС"
        else -> {
            "ВС"
        }
    }
}
