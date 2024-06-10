package put.inf154030.zwaar.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity("exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val exerciseId: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("gear_id") val gearId: Int
)
