package put.inf154030.zwaar.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import put.inf154030.zwaar.Converters
import put.inf154030.zwaar.dao.ExerciseDAO
import put.inf154030.zwaar.dao.GearDAO
import put.inf154030.zwaar.dao.PersonalDataHistoryDAO
import put.inf154030.zwaar.dao.SettingsDAO
import put.inf154030.zwaar.dao.TrainingDataHistoryDAO
import put.inf154030.zwaar.dao.TrainingPlanDAO
import put.inf154030.zwaar.dao.UserDAO
import put.inf154030.zwaar.dao.UserGearDAO
import put.inf154030.zwaar.dao.WorkoutDAO
import put.inf154030.zwaar.dao.WorkoutExerciseDAO
import put.inf154030.zwaar.entities.Exercise
import put.inf154030.zwaar.entities.Gear
import put.inf154030.zwaar.entities.Settings
import put.inf154030.zwaar.entities.TrainingPlan
import put.inf154030.zwaar.entities.User
import put.inf154030.zwaar.entities.Workout
import put.inf154030.zwaar.relations.PersonalDataHistory
import put.inf154030.zwaar.relations.TrainingDataHistory
import put.inf154030.zwaar.relations.UserGear
import put.inf154030.zwaar.relations.WorkoutExercise

@Database(
    entities = [
        User::class,
        Gear::class,
        Exercise::class,
        Workout::class,
        WorkoutExercise::class,
        UserGear::class,
        PersonalDataHistory::class,
        TrainingPlan::class,
        TrainingDataHistory::class,
        Settings::class
               ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract val userDao: UserDAO
    abstract val gearDao: GearDAO
    abstract val exerciseDao: ExerciseDAO
    abstract val workoutDao: WorkoutDAO
    abstract val workoutExerciseDao: WorkoutExerciseDAO
    abstract val userGearDao: UserGearDAO
    abstract val personalDataHistoryDao: PersonalDataHistoryDAO
    abstract val trainingPlanDao: TrainingPlanDAO
    abstract val trainingDataHistoryDao: TrainingDataHistoryDAO
    abstract val settingsDao: SettingsDAO
}