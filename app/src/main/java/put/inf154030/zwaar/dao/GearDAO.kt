package put.inf154030.zwaar.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import put.inf154030.zwaar.entities.Gear

@Dao
interface GearDAO {
    @Query("SELECT * FROM gear")
    suspend fun getAll(): List<Gear>

    @Insert
    suspend fun insertGear(gear: Gear)

    @Query("SELECT * FROM gear WHERE gearId = :gearId")
    suspend fun getGearById(gearId: Int): Gear
}