package put.inf154030.zwaar.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import put.inf154030.zwaar.relations.WorkoutExercise

@Dao
interface WorkoutExerciseDAO {

    @Query("SELECT exercise_id FROM workout_exercise WHERE workout_id = :workoutId")
    suspend fun getAllWorkoutExercises(workoutId: Int): List<Int>

    @Query("SELECT * FROM workout_exercise WHERE workout_id = :workoutId")
    suspend fun getWorkoutExerciseList(workoutId: Int): List<WorkoutExercise>

    @Insert
    suspend fun insertWorkoutExercise(workoutExercise: WorkoutExercise)

    @Update
    suspend fun updateWorkoutExercise(workoutExercise: WorkoutExercise)

    @Query("SELECT weight FROM workout_exercise WHERE workout_id = :workoutId AND exercise_id = :exerciseId")
    suspend fun getWeight(workoutId: Int, exerciseId: Int): Double

    @Query("SELECT quantity FROM workout_exercise WHERE workout_id = :workoutId AND exercise_id = :exerciseId")
    suspend fun getQuantity(workoutId: Int, exerciseId: Int): Int

    @Query("SELECT series FROM workout_exercise WHERE workout_id = :workoutId AND exercise_id = :exerciseId")
    suspend fun getSeries(workoutId: Int, exerciseId: Int): Int

    @Query("SELECT * FROM workout_exercise WHERE workout_id = :workoutId AND exercise_id = :exerciseId")
    suspend fun getWorkoutExerciseByIds(workoutId: Int, exerciseId: Int): WorkoutExercise

    @Query("SELECT * FROM workout_exercise WHERE id IN (:ids)")
    suspend fun getWorkoutExerciseByIds(ids: List<Int>): List<WorkoutExercise>
}