package put.inf154030.zwaar.entities

import androidx.room.Entity
import java.sql.Date

@Entity(tableName = "training_plans")
data class TrainingPlan(
    val userId: Int,
    val workoutId: Int,
    val date: Date
)
