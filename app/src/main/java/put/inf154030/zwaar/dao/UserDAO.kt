package put.inf154030.zwaar.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import put.inf154030.zwaar.entities.User

@Dao
interface UserDAO {
    @Insert
    suspend fun inserUser(user: User)

    @Query("SELECT password FROM users WHERE login = :login")
    suspend fun getUserPassword(login: String): String?

    @Query("SELECT userId FROM users WHERE login = :login")
    suspend fun getUserId(login: String): Int

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM users WHERE userId = :userId")
    suspend fun getUserById(userId: Int): User?
}