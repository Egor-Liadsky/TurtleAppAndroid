package com.turtleteam.storage.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.turtleteam.turtleapp.InstitutionPreferences
import java.io.InputStream
import java.io.OutputStream

object InstitutionPreferencesSerializer : Serializer<InstitutionPreferences> {

    override val defaultValue: InstitutionPreferences
        get() = InstitutionPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): InstitutionPreferences {
        try {
            return InstitutionPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto. ", exception)
        }
    }

    override suspend fun writeTo(t: InstitutionPreferences, output: OutputStream) = t.writeTo(output)
}
