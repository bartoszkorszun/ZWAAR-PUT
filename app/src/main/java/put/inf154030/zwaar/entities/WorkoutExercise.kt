package put.inf154030.zwaar.entities

import androidx.room.Entity

@Entity(tableName = "workout_exercise")
data class WorkoutExercise(
    val workoutId: Int,
    val exerciseId: Int,
    val weight: Double,
    val quantity: Int,
    val series: Int
)
