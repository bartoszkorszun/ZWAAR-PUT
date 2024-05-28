package put.inf154030.zwaar.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import put.inf154030.zwaar.R
import put.inf154030.zwaar.databinding.ActivityPersonalDataHistoryBinding
import put.inf154030.zwaar.fragments.ButtonDumbbellFragment
import put.inf154030.zwaar.fragments.NavigationBarFragment

class PersonalDataHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonalDataHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPersonalDataHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_dumbbell, ButtonDumbbellFragment())
            .replace(R.id.fragment_container_nav_bar, NavigationBarFragment())
            .commit()
    }
}