package put.inf154030.zwaar.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import put.inf154030.zwaar.entities.Exercise
import put.inf154030.zwaar.entities.Workout

@Entity(
    tableName = "workout_exercises",
    foreignKeys = [
        ForeignKey(
            entity = Workout::class,
            parentColumns = ["workoutId"],
            childColumns = ["workoutId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["exerciseId"],
            childColumns = ["exerciseId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["workoutId", "exerciseId"]
)
data class WorkoutExercise(
    val workoutId: Int,
    val exerciseId: Int,
    @ColumnInfo(name = "weight") val weight: Double,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "series") val series: Int
)
