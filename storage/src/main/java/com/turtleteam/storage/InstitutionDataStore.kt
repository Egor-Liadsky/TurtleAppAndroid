package com.turtleteam.storage

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.turtleteam.core_view.model.Institution
import com.turtleteam.storage.data.InstitutionPreferencesSerializer
import com.turtleteam.turtleapp.InstitutionPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

interface InstitutionDataStore {
    val institutionPreferences: Flow<InstitutionPreferences>
    suspend fun saveInstitution(institution: Institution)
    suspend fun getInstitution(): Institution
}

class InstitutionDataStoreImpl(private val context: Context) : InstitutionDataStore {

    companion object {
        private const val DATA_STORE_FILE_NAME = "institution_prefs.pb"
    }

    private val Context.institutionPreferencesStore: DataStore<InstitutionPreferences> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = InstitutionPreferencesSerializer,
    )

    override val institutionPreferences: Flow<InstitutionPreferences> =
        context.institutionPreferencesStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.e("TAG", "Error reading sort order preferences.", exception)
                    emit(InstitutionPreferences.getDefaultInstance())
                } else {
                    throw exception
                }
            }

    override suspend fun saveInstitution(institution: Institution) {
        context.institutionPreferencesStore.updateData { preferences ->
            preferences.toBuilder()
                .setId(institution.id)
                .setCode(institution.code)
                .setTitle(institution.title)
                .setPort(institution.port)
                .build()
        }
    }

    override suspend fun getInstitution(): Institution {
        return institutionPreferences.map {
            Institution(
                id = it.id,
                title = it.title,
                code = it.code,
                port = it.port,
            )
        }.first()
    }
}
