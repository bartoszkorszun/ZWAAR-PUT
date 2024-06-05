package put.inf154030.zwaar.DAO

import androidx.room.Dao
import androidx.room.Query
import put.inf154030.zwaar.entities.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM users")
    fun getAll(): List<User>
}