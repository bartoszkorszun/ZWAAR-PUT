package put.inf154030.zwaar.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("settings")
data class Settings(
    @PrimaryKey val id: Int,
    @ColumnInfo("notifications") val notifications: Boolean
)
