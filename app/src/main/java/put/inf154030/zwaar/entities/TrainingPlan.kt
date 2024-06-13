package put.inf154030.zwaar.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("training_plans")
data class TrainingPlan(
    @PrimaryKey(autoGenerate = true) val trainingPlanId: Int,
    @ColumnInfo("date") val date: String,
    @ColumnInfo("workout_id") val workoutId: Int,
    @ColumnInfo("user_id") val userId: Int
)
