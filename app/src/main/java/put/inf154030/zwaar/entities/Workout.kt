package put.inf154030.zwaar.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true) val workoutId: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("user_id") val userId: Int,
    @ColumnInfo("is_home") val isHome: Boolean
)
