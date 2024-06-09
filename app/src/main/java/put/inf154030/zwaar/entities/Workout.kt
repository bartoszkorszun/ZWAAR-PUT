package put.inf154030.zwaar.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true) val workoutId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "userId") val userId: Int
)
