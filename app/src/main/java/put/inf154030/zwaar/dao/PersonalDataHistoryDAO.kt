package put.inf154030.zwaar.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import put.inf154030.zwaar.relations.PersonalDataHistory

@Dao
interface PersonalDataHistoryDAO {

    @Query("SELECT * FROM personal_data_history WHERE user_id = :userId")
    suspend fun getUserHistory(userId: Int): List<PersonalDataHistory>

    @Insert
    suspend fun insertUserHistory(userHistory: PersonalDataHistory)
}