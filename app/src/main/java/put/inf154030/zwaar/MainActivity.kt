package put.inf154030.zwaar

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import put.inf154030.zwaar.activities.LogInActivity
import put.inf154030.zwaar.activities.SignUpActivity
import put.inf154030.zwaar.databases.UserDatabase
import put.inf154030.zwaar.databinding.ActivityMainBinding
import put.inf154030.zwaar.fragments.ButtonLogInFragment
import put.inf154030.zwaar.fragments.ButtonSignUpFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "zwaar.db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_buttons, ButtonSignUpFragment())
            .add(R.id.fragment_container_buttons, ButtonLogInFragment())
            .commit()
    }

    fun startSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    fun startLogInActivity() {
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }
}