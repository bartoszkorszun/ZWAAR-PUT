package put.inf154030.zwaar.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import put.inf154030.zwaar.relations.WorkoutExercise

@Dao
interface WorkoutExerciseDAO {

    @Query("SELECT exerciseId FROM workout_exercise WHERE workoutId = :workoutId")
    suspend fun getAllWorkoutExercises(workoutId: Int): List<Int>

    @Insert
    suspend fun insertWorkoutExercise(workoutExercise: WorkoutExercise)
}