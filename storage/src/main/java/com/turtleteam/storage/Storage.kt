package com.turtleteam.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

interface Storage {

    val theme: Flow<Boolean>

    suspend fun setTheme(value: Boolean)
    suspend fun saveInstitution(institution: String)
    suspend fun saveInstitutionPort(port: String)
    suspend fun saveGroup(group: String)
    suspend fun saveTeacher(teacher: String)

    suspend fun getTheme(): Boolean
    suspend fun getGroup(): String?
    suspend fun getInstitutionPort(): String?
    suspend fun getInstitution(): String?
}

class StorageImpl(private val context: Context) : Storage {

    companion object {
        private val THEME_KEY = booleanPreferencesKey("isDarkTheme")
        private val INSTITUTION_KEY = stringPreferencesKey("institution")
        private val INSTITUTION_PORT_KEY = stringPreferencesKey("institutionPort")
        private val GROUP_KEY = stringPreferencesKey("group")
        private val TEACHER_KEY = stringPreferencesKey("teacher")
        private const val DATASTORE_NAME = "storage"
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

    override val theme: Flow<Boolean>
        get() = context.dataStore.data.map { prefs ->
            prefs[THEME_KEY] ?: false
        }

    private val institution by lazy {
        context.dataStore.data.map { prefs ->
            val value = prefs[INSTITUTION_KEY]
            if (value.isNullOrBlank()) null else value
        }
    }

    private val institutionPort by lazy {
        context.dataStore.data.map { prefs ->
            val value = prefs[INSTITUTION_PORT_KEY]
            if (value.isNullOrBlank()) null else value
        }
    }

    private val group by lazy {
        context.dataStore.data.map { prefs ->
            val value = prefs[GROUP_KEY]
            if (value.isNullOrBlank()) null else value
        }
    }

//    override val teacher: Flow<String>
//        get() = context.dataStore.data.map { prefs ->
//            prefs[TEACHER_KEY] ?: ""
//        }

    override suspend fun setTheme(value: Boolean) {
        context.dataStore.edit { storage ->
            storage[THEME_KEY] = value
        }
    }

    override suspend fun saveInstitution(institution: String) {
        context.dataStore.edit { storage ->
            storage[INSTITUTION_KEY] = institution
        }
    }

    override suspend fun saveInstitutionPort(port: String) {
        context.dataStore.edit { storage ->
            storage[INSTITUTION_PORT_KEY] = port
        }
    }

    override suspend fun saveGroup(group: String) {
        context.dataStore.edit { storage ->
            storage[GROUP_KEY] = group
        }
    }

    override suspend fun saveTeacher(teacher: String) {
        context.dataStore.edit { storage ->
            storage[TEACHER_KEY] = teacher
        }
    }

    override suspend fun getTheme(): Boolean {
        return theme.first()
    }

    override suspend fun getGroup(): String? {
        return group.first()
    }

    override suspend fun getInstitutionPort(): String? {
        return institutionPort.first()
    }

    override suspend fun getInstitution(): String? {
        return institution.first()
    }
}
