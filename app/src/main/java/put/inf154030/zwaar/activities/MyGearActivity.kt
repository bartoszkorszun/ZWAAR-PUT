package put.inf154030.zwaar.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import put.inf154030.zwaar.R
import put.inf154030.zwaar.UserSession
import put.inf154030.zwaar.adapters.GearAdapter
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.databinding.ActivityMyGearBinding
import put.inf154030.zwaar.fragments.ButtonAddFragment
import put.inf154030.zwaar.fragments.ButtonDumbbellFragment
import put.inf154030.zwaar.fragments.NavigationBarFragment

class MyGearActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyGearBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMyGearBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_dumbbell, ButtonDumbbellFragment())
                .replace(R.id.fragment_container_add_button, ButtonAddFragment())
                .replace(R.id.fragment_container_nav_bar, NavigationBarFragment())
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        val context = this
        lifecycleScope.launch {
            val db = DatabaseProvider.getDatabase(context)
            val userGears = db.userGearDao.getAllUserGear(UserSession.loggedInUserId)
            val gears = db.gearDao.getAllGearById(userGears)
            val userGearList = binding.recyclerViewGearList
            userGearList.layoutManager = LinearLayoutManager(context)
            userGearList.adapter = GearAdapter(gears) {gear ->
                val intent = Intent(context, AddGearActivity::class.java)
                intent.putExtra("gear_id", gear.gearId)
                intent.putExtra("user_id", UserSession.loggedInUserId)
                startActivity(intent)
            }
        }
    }
}