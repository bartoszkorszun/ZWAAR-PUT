package put.inf154030.zwaar.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import put.inf154030.zwaar.R
import put.inf154030.zwaar.databinding.ActivityAddTrainingPlanBinding
import put.inf154030.zwaar.fragments.AddTrainingPlanFragment
import put.inf154030.zwaar.fragments.ButtonDumbbellFragment

class AddTrainingPlanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTrainingPlanBinding
    private var selectedDate: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddTrainingPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_dumbbell, ButtonDumbbellFragment())
                .replace(R.id.fragment_container_add_training_plan, AddTrainingPlanFragment())
                .commit()
        }

        selectedDate = intent.getStringExtra("selected_date").toString()
    }

    fun getSelectedDate(): String {
        return selectedDate
    }
}