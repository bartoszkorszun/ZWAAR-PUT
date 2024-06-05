package put.inf154030.zwaar.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int,
    @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "gender") val gender: String?,
    @ColumnInfo(name = "height") val height: Double?,
    @ColumnInfo(name = "weight") val weight: Double?,
    @ColumnInfo(name = "bmi") val bmi: Double?
)
