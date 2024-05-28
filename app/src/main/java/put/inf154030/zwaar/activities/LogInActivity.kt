package put.inf154030.zwaar.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import put.inf154030.zwaar.R
import put.inf154030.zwaar.databinding.ActivityLogInBinding
import put.inf154030.zwaar.fragments.ButtonLogInFragment
import put.inf154030.zwaar.fragments.FieldsLogInFragment

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_fields, FieldsLogInFragment())
            .replace(R.id.fragment_container_buttons, ButtonLogInFragment())
            .commit()
    }

    fun startHomeScreenActivity() {
        val intent = Intent(this, HomeScreenActivity::class.java)
        startActivity(intent)
    }
}