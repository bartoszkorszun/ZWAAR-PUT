package put.inf154030.zwaar.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("training_data_history")
data class TrainingDataHistory(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("user_id") val userId: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("date") val date: String,
    @ColumnInfo("workout_exercise_ids") val workoutExerciseIds: String
)
