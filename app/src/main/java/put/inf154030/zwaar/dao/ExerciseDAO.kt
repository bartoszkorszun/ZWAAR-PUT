package put.inf154030.zwaar.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import put.inf154030.zwaar.entities.Exercise

@Dao
interface ExerciseDAO {
    @Insert
    suspend fun insertExercise(exercise: Exercise)

    @Query("SELECT * FROM exercises WHERE exerciseId = :exerciseId")
    suspend fun getExerciseById(exerciseId: Int): Exercise

    @Query("SELECT * FROM exercises WHERE exerciseId IN (:ids)")
    suspend fun getAllExercisesWhereIdIn(ids: List<Int>): List<Exercise>
}