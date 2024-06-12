package put.inf154030.zwaar.activities

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import put.inf154030.zwaar.R
import put.inf154030.zwaar.UserSession
import put.inf154030.zwaar.adapters.EmptyItemAdapter
import put.inf154030.zwaar.adapters.WorkoutItemAdapter
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.databinding.ActivityTrainingPlanBinding
import put.inf154030.zwaar.fragments.ButtonAddFragment
import put.inf154030.zwaar.fragments.ButtonDumbbellFragment
import put.inf154030.zwaar.fragments.NavigationBarFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class TrainingPlanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrainingPlanBinding
    private lateinit var editTextDate: EditText
    private var selectedDate: String = ""
    @SuppressLint("ClickableViewAccessibility", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTrainingPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editTextDate = binding.editTextDate
        val currentDate = getCurrentDate()
        selectedDate = currentDate
        editTextDate.setText(currentDate)

        editTextDate.setCompoundDrawablesWithIntrinsicBounds(
            null, null, getDrawable(R.drawable.ic_action_date), null
        )
        editTextDate.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (editTextDate.right - editTextDate.compoundPaddingRight)) {
                    showDatePickerDialog()
                    return@setOnTouchListener true
                }
            }
            false
        }

        checkIfWorkoutExists(selectedDate)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_dumbbell, ButtonDumbbellFragment())
            .replace(R.id.fragment_container_nav_bar, NavigationBarFragment())
            .commit()
    }

    override fun onResume() {
        super.onResume()

        checkIfWorkoutExists(selectedDate)
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = Calendar.getInstance().time
        return dateFormat.format(date)
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
            editTextDate.setText(selectedDate)
            this.selectedDate = selectedDate
            checkIfWorkoutExists(this.selectedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun checkIfWorkoutExists(date: String) {
        val userId = UserSession.loggedInUserId
        val convertedDate = convertDateFormat(date)
        val context = this
        val recyclerView = binding.recyclerViewTrainingPlan
        recyclerView.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            val db = DatabaseProvider.getDatabase(context)
            val todaysWorkout = db.trainingPlanDao.getTrainingPlanByDate(convertedDate, userId)
            if (todaysWorkout != null) {
                val workout = db.workoutDao.getWorkoutById(todaysWorkout.workoutId)
                val adapter = WorkoutItemAdapter(workout)
                recyclerView.adapter = adapter
                val fragment = supportFragmentManager.findFragmentByTag("ButtonAddFragmentTag")
                if (fragment != null) {
                    supportFragmentManager.beginTransaction()
                        .remove(fragment)
                        .commit()
                }
            } else {
                val adapter = EmptyItemAdapter()
                recyclerView.adapter = adapter
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_add_button, ButtonAddFragment(), "ButtonAddFragmentTag")
                    .commit()
            }
        }
    }

    private fun convertDateFormat(inputDate: String): String {
        val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val date = inputFormat.parse(inputDate)

        return outputFormat.format(date as Date)
    }
}