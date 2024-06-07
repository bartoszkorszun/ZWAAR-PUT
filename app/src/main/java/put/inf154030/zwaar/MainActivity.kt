package put.inf154030.zwaar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.databinding.ActivityMainBinding
import put.inf154030.zwaar.fragments.ButtonLogInFragment
import put.inf154030.zwaar.fragments.ButtonSignUpFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_buttons, ButtonSignUpFragment())
            .add(R.id.fragment_container_buttons, ButtonLogInFragment())
            .commit()

        lifecycleScope.launch {
            checkGear()
            checkExercises()
        }
    }

    private suspend fun checkGear() {
        val db = DatabaseProvider.getDatabase(this)
        val gearCheck = db.gearDao.getGearById(1)
        if (gearCheck != null) return
        Gear.gearList.forEach {gear ->
            db.gearDao.insertGear(gear)
        }
    }

    private suspend fun checkExercises() {
        val db = DatabaseProvider.getDatabase(this)
        val exeCheck = db.exerciseDao.getExerciseById(1)
        if (exeCheck != null) return
        Exercises.exercisesList.forEach {exercise ->
            db.exerciseDao.insertExercise(exercise)
        }
    }
}