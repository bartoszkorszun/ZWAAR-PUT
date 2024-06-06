package put.inf154030.zwaar.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey val exerciseId: Int,
    @ColumnInfo(name = "name") val name: String,
)
