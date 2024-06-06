package put.inf154030.zwaar.entities

import androidx.room.Entity

@Entity(tableName = "exercises")
data class Exercise(
    val exerciseId: Int,
    val name: String,
    val gearId: Int
)
