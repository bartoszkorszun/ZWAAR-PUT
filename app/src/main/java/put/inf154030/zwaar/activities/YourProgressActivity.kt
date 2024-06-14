package put.inf154030.zwaar.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import put.inf154030.zwaar.R
import put.inf154030.zwaar.databinding.ActivityYourProgressBinding
import put.inf154030.zwaar.fragments.ButtonDumbbellFragment
import put.inf154030.zwaar.fragments.NavigationBarFragment
import put.inf154030.zwaar.fragments.YourProgressOptionsFragment

class YourProgressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityYourProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityYourProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_dumbbell, ButtonDumbbellFragment())
                .replace(R.id.fragment_container_options, YourProgressOptionsFragment())
                .replace(R.id.fragment_container_nav_bar, NavigationBarFragment())
                .commit()
        }
    }
}