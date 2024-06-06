package put.inf154030.zwaar.relations

import androidx.room.Embedded
import androidx.room.Relation
import put.inf154030.zwaar.entities.Exercise
import put.inf154030.zwaar.entities.Gear

data class ExerciseGear(
    @Embedded val gear: Gear,
    @Relation(
        parentColumn = "gearId",
        entityColumn = "exerciseId"
    )
    val exercises: List<Exercise>
)
