package put.inf154030.zwaar.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import put.inf154030.zwaar.R
import put.inf154030.zwaar.UserSession
import put.inf154030.zwaar.adapters.WorkoutAdapter
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.databinding.ActivityAllWorkoutsBinding
import put.inf154030.zwaar.fragments.ButtonAddFragment
import put.inf154030.zwaar.fragments.ButtonAddGearFragment
import put.inf154030.zwaar.fragments.ButtonDumbbellFragment
import put.inf154030.zwaar.fragments.NavigationBarFragment

class AllWorkoutsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllWorkoutsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAllWorkoutsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_dumbbell, ButtonDumbbellFragment())
                .replace(R.id.fragment_container_add_button, ButtonAddFragment())
                .replace(R.id.fragment_container_add_gear, ButtonAddGearFragment())
                .replace(R.id.fragment_container_nav_bar, NavigationBarFragment())
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        val context = this
        lifecycleScope.launch {
            val db = DatabaseProvider.getDatabase(context)
            val workouts = db.workoutDao.getAllUserWorkouts(UserSession.loggedInUserId)
            val workoutList = binding.recyclerViewWorkoutsList
            workoutList.layoutManager = LinearLayoutManager(context)
            workoutList.adapter = WorkoutAdapter(workouts) {workout ->
                val intent = Intent(context, WorkoutActivity::class.java)
                intent.putExtra("workout_name", workout.name)
                intent.putExtra("workout_id", workout.workoutId)
                startActivity(intent)
            }
        }
    }
}