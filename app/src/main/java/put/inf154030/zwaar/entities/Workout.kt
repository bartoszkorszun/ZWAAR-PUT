package put.inf154030.zwaar.entities

import androidx.room.Entity

@Entity(tableName = "workouts")
data class Workout(
    val workoutId: Int,
    val name: String,
    val userId: Int
)
