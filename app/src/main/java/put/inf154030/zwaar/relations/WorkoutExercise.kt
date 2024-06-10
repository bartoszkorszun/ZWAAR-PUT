package put.inf154030.zwaar.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("workout_exercise")
data class WorkoutExercise(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("workout_id") val workoutId: Int,
    @ColumnInfo("exercise_id") val exerciseId: Int,
    @ColumnInfo("weight") val weight: Double,
    @ColumnInfo("quantity") val quantity: Int,
    @ColumnInfo("series") val series: Int
)
