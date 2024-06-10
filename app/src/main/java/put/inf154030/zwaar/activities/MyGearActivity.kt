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
import put.inf154030.zwaar.adapters.ExerciseAdapter
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.databinding.ActivityMyGearBinding
import put.inf154030.zwaar.fragments.ButtonAddFragment
import put.inf154030.zwaar.fragments.ButtonDumbbellFragment
import put.inf154030.zwaar.fragments.NavigationBarFragment

class MyGearActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyGearBinding
    private val db = DatabaseProvider.getDatabase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMyGearBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_dumbbell, ButtonDumbbellFragment())
            .replace(R.id.fragment_container_add_button, ButtonAddFragment())
            .replace(R.id.fragment_container_nav_bar, NavigationBarFragment())
            .commit()
    }

    override fun onResume() {
        super.onResume()
        val context = this
//        lifecycleScope.launch {
//            val userGears = db.userGearDao.getAllUserGear(UserSession.loggedInUserId)
//            val gears = db.gearDao.get
//            val workoutExerciseList = binding.recyclerViewExercisesList
//            workoutExerciseList.layoutManager = LinearLayoutManager(context)
//            workoutExerciseList.adapter = ExerciseAdapter(exercises) {exercise ->
//                val intent = Intent(context, AddExerciseActivity::class.java)
//                intent.putExtra("exercise_id", exercise.exerciseId)
//                intent.putExtra("workout_id", workoutId)
//                startActivity(intent)
//            }
//        }
    }
}