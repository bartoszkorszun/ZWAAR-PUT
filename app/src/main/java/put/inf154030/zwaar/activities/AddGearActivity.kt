package put.inf154030.zwaar.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import put.inf154030.zwaar.R
import put.inf154030.zwaar.databinding.ActivityAddGearBinding
import put.inf154030.zwaar.fragments.AddGearFragment
import put.inf154030.zwaar.fragments.ButtonDumbbellFragment

class AddGearActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddGearBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddGearBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_dumbbell, ButtonDumbbellFragment())
                .replace(R.id.fragment_container_add_gear, AddGearFragment())
                .commit()
        }
    }
}