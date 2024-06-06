package put.inf154030.zwaar.entities

import androidx.room.Entity

@Entity(tableName = "gear")
data class Gear(
    val gearId: Int,
    val name: String
)
