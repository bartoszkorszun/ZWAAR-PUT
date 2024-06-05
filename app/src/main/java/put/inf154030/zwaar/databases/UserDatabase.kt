package put.inf154030.zwaar.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import put.inf154030.zwaar.DAO.UserDAO
import put.inf154030.zwaar.entities.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {

    abstract val dao: UserDAO
}