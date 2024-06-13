package put.inf154030.zwaar.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import put.inf154030.zwaar.entities.Settings

@Dao
interface SettingsDAO {

    @Query("SELECT notifications FROM settings")
    suspend fun getSettings(): Boolean

    @Query("SELECT * FROM settings")
    suspend fun getAll(): Settings

    @Insert
    suspend fun initSettings(settings: Settings)

    @Update
    suspend fun updateSettings(settings: Settings)
}