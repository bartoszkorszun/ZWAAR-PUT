package put.inf154030.zwaar.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import put.inf154030.zwaar.R
import put.inf154030.zwaar.UserSession
import put.inf154030.zwaar.adapters.PersonalDataHistoryAdapter
import put.inf154030.zwaar.database.DatabaseProvider
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

    override fun onResume() {
        super.onResume()
        val context = this
        lifecycleScope.launch {
            val db = DatabaseProvider.getDatabase(context)
            val userHistory = db.personalDataHistoryDao.getUserHistory(UserSession.loggedInUserId)
            val userHistoryList = binding.recyclerViewUserHistoryList
            userHistoryList.layoutManager = LinearLayoutManager(context)
            userHistoryList.adapter = PersonalDataHistoryAdapter(userHistory)
        }
    }
}