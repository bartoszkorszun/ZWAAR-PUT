package put.inf154030.zwaar.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import put.inf154030.zwaar.entities.Exercise
import put.inf154030.zwaar.entities.Workout

@Entity(tableName = "workout_exercise")
data class WorkoutExercise(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "workoutId") val workoutId: Int,
    @ColumnInfo(name = "exerciseId") val exerciseId: Int,
    @ColumnInfo(name = "weight") val weight: Double,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "series") val series: Int
)
