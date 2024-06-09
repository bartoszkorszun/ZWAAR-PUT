package put.inf154030.zwaar.database

import androidx.room.Database
import androidx.room.RoomDatabase
import put.inf154030.zwaar.dao.ExerciseDAO
import put.inf154030.zwaar.dao.GearDAO
import put.inf154030.zwaar.dao.UserDAO
import put.inf154030.zwaar.dao.WorkoutDAO
import put.inf154030.zwaar.dao.WorkoutExerciseDAO
import put.inf154030.zwaar.entities.Exercise
import put.inf154030.zwaar.entities.Gear
import put.inf154030.zwaar.entities.User
import put.inf154030.zwaar.entities.Workout
import put.inf154030.zwaar.relations.WorkoutExercise

@Database(
    entities = [
        User::class,
        Gear::class,
        Exercise::class,
        Workout::class,
        WorkoutExercise::class
               ],
    version = 1
)
abstract class Database : RoomDatabase() {

    abstract val userDao: UserDAO
    abstract val gearDao: GearDAO
    abstract val exerciseDao: ExerciseDAO
    abstract val workoutDao: WorkoutDAO
    abstract val workoutExerciseDao: WorkoutExerciseDAO
}