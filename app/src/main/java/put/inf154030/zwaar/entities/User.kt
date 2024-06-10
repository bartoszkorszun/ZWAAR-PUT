package put.inf154030.zwaar.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("users")
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int,
    @ColumnInfo("login") val login: String,
    @ColumnInfo("password") val password: String,
    @ColumnInfo("email") val email: String,
    @ColumnInfo("gender") val gender: String?,
    @ColumnInfo("height") val height: Double?,
    @ColumnInfo("weight") val weight: Double?,
    @ColumnInfo("bmi") val bmi: Double?
)
