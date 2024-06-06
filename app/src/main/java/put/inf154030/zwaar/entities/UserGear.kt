package put.inf154030.zwaar.entities

import androidx.room.Entity

@Entity(tableName = "user_gear")
data class UserGear(
    val userId: Int,
    val gearId: Int
)
