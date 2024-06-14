package put.inf154030.zwaar.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import put.inf154030.zwaar.R
import put.inf154030.zwaar.adapters.ExerciseAdapter
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.databinding.ActivityWorkoutBinding
import put.inf154030.zwaar.fragments.ButtonAddFragment
import put.inf154030.zwaar.fragments.ButtonDumbbellFragment
import put.inf154030.zwaar.fragments.NavigationBarFragment

class WorkoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkoutBinding
    private var workoutId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_dumbbell, ButtonDumbbellFragment())
                .replace(R.id.fragment_container_add_button, ButtonAddFragment())
                .replace(R.id.fragment_container_nav_bar, NavigationBarFragment())
                .commit()
        }

        binding.textViewWorkoutName.text = intent.getStringExtra("workout_name")
        workoutId = intent.getIntExtra("workout_id", 0)
    }

    override fun onResume() {
        super.onResume()
        val context = this
        lifecycleScope.launch {
            val db = DatabaseProvider.getDatabase(context)
            val workoutExercises = db.workoutExerciseDao.getAllWorkoutExercises(workoutId)
            val exercises = db.exerciseDao.getAllExercisesWhereIdIn(workoutExercises)
            val workoutExerciseList = binding.recyclerViewExercisesList
            workoutExerciseList.layoutManager = LinearLayoutManager(context)
            workoutExerciseList.adapter = ExerciseAdapter(exercises) {exercise ->
                val intent = Intent(context, AddExerciseActivity::class.java)
                intent.putExtra("exercise_id", exercise.exerciseId)
                intent.putExtra("workout_id", workoutId)
                startActivity(intent)
            }
        }
    }

    fun getWorkoutId(): Int {
        return workoutId
    }
}