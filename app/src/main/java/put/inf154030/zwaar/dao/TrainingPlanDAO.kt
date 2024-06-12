package put.inf154030.zwaar.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import put.inf154030.zwaar.entities.TrainingPlan

@Dao
interface TrainingPlanDAO {

    @Insert
    suspend fun insertTrainingPlan(trainingPlan: TrainingPlan)

    @Query("SELECT * FROM training_plans WHERE date = :date AND user_id = :userId")
    suspend fun getTrainingPlanByDate(date: String, userId: Int): TrainingPlan
}