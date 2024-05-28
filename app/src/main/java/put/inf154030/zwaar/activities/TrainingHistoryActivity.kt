package put.inf154030.zwaar.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import put.inf154030.zwaar.R
import put.inf154030.zwaar.databinding.ActivityTrainingHistoryBinding
import put.inf154030.zwaar.fragments.ButtonDumbbellFragment
import put.inf154030.zwaar.fragments.NavigationBarFragment

class TrainingHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrainingHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTrainingHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_dumbbell, ButtonDumbbellFragment())
            .replace(R.id.fragment_container_nav_bar, NavigationBarFragment())
            .commit()
    }
}