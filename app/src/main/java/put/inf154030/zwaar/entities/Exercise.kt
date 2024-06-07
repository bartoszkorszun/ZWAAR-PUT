package put.inf154030.zwaar.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises",
    foreignKeys = [
        ForeignKey(
            entity = Gear::class,
            parentColumns = ["gearId"],
            childColumns = ["gearId"]
        )
    ]
)
data class Exercise(
    @PrimaryKey(autoGenerate = true) val exerciseId: Int,
    @ColumnInfo(name = "name") val name: String,
    val gearId: Int
)
