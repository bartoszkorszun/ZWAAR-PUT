package put.inf154030.zwaar.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import put.inf154030.zwaar.relations.WorkoutExercise

@Dao
interface WorkoutExerciseDAO {

    @Query("SELECT exerciseId FROM workout_exercise WHERE workoutId = :workoutId")
    suspend fun getAllWorkoutExercises(workoutId: Int): List<Int>

    @Insert
    suspend fun insertWorkoutExercise(workoutExercise: WorkoutExercise)

    @Update
    suspend fun updateWorkoutExercise(workoutExercise: WorkoutExercise)

    @Query("SELECT weight FROM workout_exercise WHERE workoutId = :workoutId AND exerciseId = :exerciseId")
    suspend fun getWeight(workoutId: Int, exerciseId: Int): Double

    @Query("SELECT quantity FROM workout_exercise WHERE workoutId = :workoutId AND exerciseId = :exerciseId")
    suspend fun getQuantity(workoutId: Int, exerciseId: Int): Int

    @Query("SELECT series FROM workout_exercise WHERE workoutId = :workoutId AND exerciseId = :exerciseId")
    suspend fun getSeries(workoutId: Int, exerciseId: Int): Int

    @Query("SELECT * FROM workout_exercise WHERE workoutId = :workoutId AND exerciseId = :exerciseId")
    suspend fun getWorkoutExerciseByIds(workoutId: Int, exerciseId: Int): WorkoutExercise
}