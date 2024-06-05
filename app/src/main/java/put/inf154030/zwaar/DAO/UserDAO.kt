package put.inf154030.zwaar.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapColumn
import androidx.room.Query
import put.inf154030.zwaar.entities.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM users")
    suspend fun getAll(): List<User>

    @Insert
    suspend fun inserUser(user: User)

    @Query("SELECT password FROM users WHERE login = :login")
    suspend fun getUserPassword(login: String): String?
}