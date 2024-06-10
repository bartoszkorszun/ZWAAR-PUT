package put.inf154030.zwaar.entities

import androidx.room.Entity
import java.sql.Date

@Entity("training_plans")
data class TrainingPlan(
    val trainingPlanId: Int,
    val date: Date
)
