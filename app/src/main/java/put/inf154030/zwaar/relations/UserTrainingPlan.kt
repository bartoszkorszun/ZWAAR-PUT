package put.inf154030.zwaar.relations

import androidx.room.Embedded
import androidx.room.Relation
import put.inf154030.zwaar.entities.TrainingPlan
import put.inf154030.zwaar.entities.User

data class UserTrainingPlan(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "trainingPlanId"
    )
    val traningPlans: List<TrainingPlan>
)
