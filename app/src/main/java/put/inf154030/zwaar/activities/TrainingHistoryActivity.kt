package put.inf154030.zwaar.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import put.inf154030.zwaar.R
import put.inf154030.zwaar.UserSession
import put.inf154030.zwaar.adapters.TrainingHistoryAdapter
import put.inf154030.zwaar.database.DatabaseProvider
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

        val context = this
        val trainingHistoryList = binding.recyclerViewTrainingHistory
        lifecycleScope.launch {
            val db = DatabaseProvider.getDatabase(context)
            val trainingHistory = db.trainingDataHistoryDao.getAllUserTrainingHistory(UserSession.loggedInUserId)
            trainingHistoryList.layoutManager = LinearLayoutManager(context)
            trainingHistoryList.adapter = TrainingHistoryAdapter(trainingHistory, context)
        }
    }
}