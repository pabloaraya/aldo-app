package com.qubits.fw3.coredb.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.clear
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.*
import java.util.*

class AppDataStore constructor(context: Context) : PreferencesDataStore {

    private val sharedPreferences = context.getSharedPreferences(DATA_STORE_NAME_OTHER, Context.MODE_PRIVATE)

    private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences> =
        context.createDataStore(
            name = DATA_STORE_NAME
        )

    override suspend fun save(key: String, value: String) {
        dataStore.edit { preferences ->
            preferences[preferencesKey<String>(key)] = value
        }
    }

    override suspend fun save(key: String, value: Int) {
        dataStore.edit { preferences ->
            preferences[preferencesKey<Int>(key)] = value
        }
    }

    override suspend fun save(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[preferencesKey<Boolean>(key)] = value
        }
    }

    override suspend fun save(key: String, value: Double) {
        dataStore.edit { preferences ->
            preferences[preferencesKey<Double>(key)] = value
        }
    }

    override suspend fun save(key: String, value: Float) {
        dataStore.edit { preferences ->
            preferences[preferencesKey<Float>(key)] = value
        }
    }

    override suspend fun save(key: String, value: Long) {
        dataStore.edit { preferences ->
            preferences[preferencesKey<Long>(key)] = value
        }
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }

    override fun fetchString(key: String, defaultValue: String): Flow<String?> =
        dataStore.data.catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }.map { preferences ->
            preferences[preferencesKey(key)] ?: defaultValue
        }

    override fun fetchInt(key: String, defaultValue: Int): Flow<Int?> =
        dataStore.data.catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }.map { preferences ->
            preferences[preferencesKey(key)] ?: defaultValue
        }

    override fun fetchBoolean(key: String, defaultValue: Boolean): Flow<Boolean?> =
        dataStore.data.catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }.map { preferences ->
            preferences[preferencesKey(key)] ?: defaultValue
        }

    override fun fetchDouble(key: String, defaultValue: Double): Flow<Double?> =
        dataStore.data.catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }.map { preferences ->
            preferences[preferencesKey(key)] ?: defaultValue
        }

    override fun fetchFloat(key: String, defaultValue: Float): Flow<Float?> =
        dataStore.data.catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }.map { preferences ->
            preferences[preferencesKey(key)] ?: defaultValue
        }

    override fun fetchLong(key: String, defaultValue: Long): Flow<Long?> =
        dataStore.data.catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }.map { preferences ->
            preferences[preferencesKey(key)] ?: defaultValue
        }

    //new read, write or delete data new
    override suspend fun <T> write(key: String, value: T) =
        with(sharedPreferences.edit()) {
            val output = objectToBytes(value)

            putString(key, output)
            apply()
        }

    override suspend fun <T> read(key: String): T? {
        val first = sharedPreferences.getString(key, null)
        return try {
            bytesToObject(first!!)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun <T> read(key: String, default: T): T {
        return read(key) ?: default
    }

    override suspend fun delete(key: String) = sharedPreferences.edit().remove(key).apply()


    private fun <T> bytesToObject(bytes: String): T {
        val split = bytes.substring(1, bytes.length - 1).split(", ")

        val array = ByteArray(split.size)
        for (i in split.indices) {
            array[i] = java.lang.Byte.parseByte(split[i])
        }

        val bis = ByteArrayInputStream(array)
        val ois = ObjectInputStream(bis)

        val result = ois.readObject()

        ois.close()

        return result as T
    }

    private fun <T> objectToBytes(value: T): String {
        val bos = ByteArrayOutputStream()

        ObjectOutputStream(bos).use {
            it.writeObject(value)
            it.flush()
        }

        val resultBytes = bos.toByteArray()

        bos.close()

        return Arrays.toString(resultBytes)
    }

    companion object {
        const val DATA_STORE_NAME = "food_wallet_app_preferences"
        const val DATA_STORE_NAME_OTHER = "food_wallet_app_preferences"
    }
}
