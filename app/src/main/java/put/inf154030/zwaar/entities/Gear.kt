package put.inf154030.zwaar.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gear")
data class Gear(
    @PrimaryKey(autoGenerate = true) val gearId: Int,
    @ColumnInfo(name = "name") val name: String
)
