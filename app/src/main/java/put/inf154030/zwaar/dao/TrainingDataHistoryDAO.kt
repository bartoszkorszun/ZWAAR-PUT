package put.inf154030.zwaar.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import put.inf154030.zwaar.relations.TrainingDataHistory

@Dao
interface TrainingDataHistoryDAO {
    @Query("SELECT * FROM training_data_history WHERE date = :today AND user_id = :userId")
    suspend fun getTodaysData(today: String, userId: Int): TrainingDataHistory
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(trainingDataHistory: TrainingDataHistory)
    @Update
    suspend fun update(trainingDataHistory: TrainingDataHistory)
    @Query("SELECT * FROM training_data_history WHERE user_id = :userId")
    suspend fun getAllUserTrainingHistory(userId: Int): List<TrainingDataHistory>
}