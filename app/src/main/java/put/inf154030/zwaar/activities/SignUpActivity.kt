package put.inf154030.zwaar.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import put.inf154030.zwaar.R
import put.inf154030.zwaar.databinding.ActivitySignUpBinding
import put.inf154030.zwaar.fragments.ButtonSignUpFragment
import put.inf154030.zwaar.fragments.FieldsSignUpFragment

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_fields, FieldsSignUpFragment())
            .replace(R.id.fragment_container_button, ButtonSignUpFragment())
            .commit()
    }

    fun startHomeScreenActivity() {
        val intent = Intent(this, HomeScreenActivity::class.java)
        startActivity(intent)
    }
}