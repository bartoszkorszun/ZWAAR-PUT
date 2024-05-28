package put.inf154030.zwaar.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import put.inf154030.zwaar.R
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

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_dumbbell, ButtonDumbbellFragment())
            .replace(R.id.fragment_container_add_button, ButtonAddFragment())
            .replace(R.id.fragment_container_add_gear, ButtonAddGearFragment())
            .replace(R.id.fragment_container_nav_bar, NavigationBarFragment())
            .commit()
    }
}