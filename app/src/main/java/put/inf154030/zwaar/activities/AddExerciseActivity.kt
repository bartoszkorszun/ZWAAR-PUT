package put.inf154030.zwaar.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import put.inf154030.zwaar.R
import put.inf154030.zwaar.databinding.ActivityAddExerciseBinding
import put.inf154030.zwaar.fragments.AddExerciseFragment
import put.inf154030.zwaar.fragments.ButtonDumbbellFragment

class AddExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddExerciseBinding
    private var workoutId: Int = -1
    private var exerciseId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_dumbbell, ButtonDumbbellFragment())
                .replace(R.id.fragment_container_add_exercise, AddExerciseFragment())
                .commit()
        }

        workoutId = intent.getIntExtra("workout_id", 0)
        exerciseId = intent.getIntExtra("exercise_id", 0)
    }

    fun getWorkoutId(): Int {
        return workoutId
    }

    fun getExerciseId(): Int {
        return exerciseId
    }
}