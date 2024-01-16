package com.turtleteam.storage

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.turtleteam.core_view.model.Schedule
import com.turtleteam.storage.data.InstitutionPreferencesSerializer
import com.turtleteam.storage.data.SchedulePreferencesSerializer
import com.turtleteam.turtleapp.InstitutionPreferences
import com.turtleteam.turtleapp.SchedulePreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

interface GroupDataStore {

    val schedule: Flow<SchedulePreferences>

    suspend fun saveGroupSchedule(schedule: Schedule)
    suspend fun getGroupSchedule(): Schedule
}

class GroupDataStoreImpl(private val context: Context): GroupDataStore {

    companion object {
        private const val DATA_STORE_FILE_NAME = "group_schedule.pb"
    }

    private val Context.groupSchedulePreferences: DataStore<SchedulePreferences> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = SchedulePreferencesSerializer,
    )

    override val schedule: Flow<SchedulePreferences>
        get() = context.groupSchedulePreferences.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.e("TAG", "Error reading sort order preferences.", exception)
                    emit(SchedulePreferences.getDefaultInstance())
                } else {
                    throw exception
                }
            }

    override suspend fun saveGroupSchedule(schedule: Schedule) {
//        context.groupSchedulePreferences.updateData { preferences ->
//            preferences.toBuilder()
//                .setDays()
//        }
    }

    override suspend fun getGroupSchedule(): Schedule {
        TODO("Not yet implemented")
    }
}