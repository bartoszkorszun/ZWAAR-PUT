package put.inf154030.zwaar.entities

import androidx.room.Entity

@Entity(tableName = "settings")
data class Settings(
    val notifications: Boolean
)
