package put.inf154030.zwaar.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import put.inf154030.zwaar.R
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.databinding.ActivitySettingsBinding
import put.inf154030.zwaar.entities.Settings
import put.inf154030.zwaar.fragments.ButtonDumbbellFragment
import put.inf154030.zwaar.fragments.NavigationBarFragment

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_dumbbell, ButtonDumbbellFragment())
                .replace(R.id.fragment_container_nav_bar, NavigationBarFragment())
                .commit()
        }

        val notifications = binding.switchNotifications
        val context = this
        val db = DatabaseProvider.getDatabase(context)
        lifecycleScope.launch {
            notifications.isChecked = db.settingsDao.getSettings()
        }

        notifications.setOnCheckedChangeListener { _, isChecked ->
            val settings = Settings(
                1,
                isChecked
            )

            lifecycleScope.launch {
                db.settingsDao.updateSettings(settings)
            }
        }
    }
}