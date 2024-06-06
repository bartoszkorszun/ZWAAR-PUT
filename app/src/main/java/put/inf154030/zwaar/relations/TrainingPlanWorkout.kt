package put.inf154030.zwaar.relations

import androidx.room.Embedded
import androidx.room.Relation
import put.inf154030.zwaar.entities.TrainingPlan
import put.inf154030.zwaar.entities.Workout

data class TrainingPlanWorkout(
    @Embedded val trainingPlan: TrainingPlan,
    @Relation(
        parentColumn = "trainingPlanId",
        entityColumn = "workoutId"
    )
    val workouts: List<Workout>
)
