package com.turtleteam.storage.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.turtleteam.turtleapp.SchedulePreferences
import java.io.InputStream
import java.io.OutputStream

object SchedulePreferencesSerializer : Serializer<SchedulePreferences> {

    override val defaultValue: SchedulePreferences
        get() = SchedulePreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SchedulePreferences {
        try {
            return SchedulePreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto. ", exception)
        }
    }

    override suspend fun writeTo(t: SchedulePreferences, output: OutputStream) = t.writeTo(output)
}