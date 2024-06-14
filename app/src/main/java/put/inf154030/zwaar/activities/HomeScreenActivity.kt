package put.inf154030.zwaar.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import put.inf154030.zwaar.R
import put.inf154030.zwaar.UserSession
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.databinding.ActivityHomeScreenBinding
import put.inf154030.zwaar.fragments.MainOptionsFragment
import put.inf154030.zwaar.fragments.NavigationBarFragment

class HomeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_options, MainOptionsFragment())
                .replace(R.id.fragment_container_nav_bar, NavigationBarFragment())
                .commit()
        }

        val context = this
        lifecycleScope.launch {
            val db = DatabaseProvider.getDatabase(context)
            val userLogin = db.userDao.getUserLogin(UserSession.loggedInUserId)
            binding.textViewName.text = "Hi, $userLogin!"
        }
    }
}