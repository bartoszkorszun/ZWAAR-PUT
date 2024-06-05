package put.inf154030.zwaar.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    private var instance: Database? = null

    fun getDatabase(context: Context): Database {
        return instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }
    }

    private fun buildDatabase(context: Context): Database {
        return Room.databaseBuilder(
            context.applicationContext,
            Database::class.java,
            "zwaar.db"
        ).build()
    }
}