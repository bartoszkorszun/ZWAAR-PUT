package put.inf154030.zwaar.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import put.inf154030.zwaar.entities.Gear
import put.inf154030.zwaar.relations.UserGear

@Dao
interface UserGearDAO {

    @Query("SELECT gear_id FROM user_gear WHERE user_id = :userId")
    suspend fun getAllUserGear(userId: Int): List<Int>

    @Insert
    suspend fun insertUserGear(userGear: UserGear)
}