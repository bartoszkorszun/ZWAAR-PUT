package put.inf154030.zwaar.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import put.inf154030.zwaar.Converters
import put.inf154030.zwaar.R
import put.inf154030.zwaar.UserSession
import put.inf154030.zwaar.adapters.TodaysWorkoutAdapter
import put.inf154030.zwaar.database.Database
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.databinding.ActivityTodaysWorkoutBinding
import put.inf154030.zwaar.entities.TrainingPlan
import put.inf154030.zwaar.fragments.ButtonDumbbellFragment
import put.inf154030.zwaar.fragments.NavigationBarFragment
import put.inf154030.zwaar.relations.TrainingDataHistory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TodaysWorkoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodaysWorkoutBinding
    private val doneExercisesList = mutableListOf<Int>()
    private var isInsert = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTodaysWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_dumbbell, ButtonDumbbellFragment())
                .replace(R.id.fragment_container_nav_bar, NavigationBarFragment())
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        val context = this
        val today = getCurrentDate()
        val userId = UserSession.loggedInUserId
        val workoutName = binding.textViewWorkoutName
        lifecycleScope.launch {
            val db = DatabaseProvider.getDatabase(context)
            println(db.trainingDataHistoryDao.getAllUserTrainingHistory(userId))
            val todaysWorkout = db.trainingPlanDao.getTrainingPlanByDate(today, userId)
            val exerciseList = binding.recyclerViewExercisesList
            if (todaysWorkout != null) {
                val workoutExercises = db.workoutExerciseDao.getWorkoutExerciseList(todaysWorkout.workoutId)
                val workout = db.workoutDao.getWorkoutById(todaysWorkout.workoutId)
                workoutName.text = workout.name
                exerciseList.layoutManager = LinearLayoutManager(context)
                exerciseList.adapter = TodaysWorkoutAdapter(workoutExercises, context) { workoutExercise, isChecked ->
                    CoroutineScope(Dispatchers.IO).launch {
                        if (isChecked) {
                            doneExercisesList.add(workoutExercise.id)
                            if (!isInsert) {
                                val data = db.trainingDataHistoryDao.getTodaysData(today, userId)
                                val updatedData = data.let {
                                    data.copy(
                                        it.id,
                                        userId,
                                        it.name,
                                        it.date,
                                        Converters().fromIntList(doneExercisesList)
                                    )
                                }
                                db.trainingDataHistoryDao.update(updatedData)
                                checkIfAllDone(db, todaysWorkout.workoutId, doneExercisesList, todaysWorkout)
                            } else {
                                val trainingDataHistory = TrainingDataHistory(
                                    0,
                                    userId,
                                    workout.name,
                                    today,
                                    Converters().fromIntList(doneExercisesList)
                                )
                                db.trainingDataHistoryDao.insert(trainingDataHistory)
                                isInsert = false
                                checkIfAllDone(db, todaysWorkout.workoutId, doneExercisesList, todaysWorkout)
                            }
                        } else {
                            doneExercisesList.remove(workoutExercise.id)
                            val data = db.trainingDataHistoryDao.getTodaysData(today, userId)
                            val updatedData = data.let {
                                data.copy(
                                    it.id,
                                    userId,
                                    it.name,
                                    it.date,
                                    Converters().fromIntList(doneExercisesList)
                                )
                            }
                            db.trainingDataHistoryDao.update(updatedData)
                            checkIfAllDone(db, todaysWorkout.workoutId, doneExercisesList, todaysWorkout)
                        }
                    }
                }
            }
        }
    }

    private suspend fun checkIfAllDone(db: Database, workoutId: Int, exerciseList: List<Int>, todaysWorkout: TrainingPlan) {
        val workoutExerciseList = db.workoutExerciseDao.getWorkoutExerciseList(workoutId)
        if (workoutExerciseList.size == exerciseList.size) {
            db.trainingPlanDao.deleteTrainingPlan(todaysWorkout)
            val thisActivity = this
            withContext(Dispatchers.Main) {
                val alertDialog = AlertDialog.Builder(thisActivity)
                    .setMessage("Training finished, your progress has been saved to database!")
                    .setPositiveButton("Ok") { _, _ ->
                        thisActivity.finish()
                    }
                    .create()
                alertDialog.show()
            }
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = Calendar.getInstance().time
        return dateFormat.format(date)
    }
}