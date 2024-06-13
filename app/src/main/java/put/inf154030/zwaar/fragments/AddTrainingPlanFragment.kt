package put.inf154030.zwaar.fragments

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import put.inf154030.zwaar.NotificationReceiver
import put.inf154030.zwaar.R
import put.inf154030.zwaar.UserSession
import put.inf154030.zwaar.activities.AddTrainingPlanActivity
import put.inf154030.zwaar.activities.TrainingPlanActivity
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.entities.TrainingPlan
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddTrainingPlanFragment : Fragment() {

    private lateinit var workoutMap: Map<String, Int>
    var workoutId: Int = -1
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_training_plan, container, false)
        val autoCompleteTextView = view.findViewById<AutoCompleteTextView>(R.id.auto_complete_select_workout)
        val addButton = view.findViewById<Button>(R.id.button_add)

        val selectedDate = (activity as AddTrainingPlanActivity).getSelectedDate()

        val userId = UserSession.loggedInUserId
        lifecycleScope.launch {
            val db = DatabaseProvider.getDatabase(requireActivity())
            val userWorkoutList = db.workoutDao.getAllUserWorkouts(userId)
            val workoutNames = userWorkoutList.map { it.name }
            workoutMap = userWorkoutList.associateBy({ it.name }, { it.workoutId })
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, workoutNames)
            autoCompleteTextView.setAdapter(adapter)
        }

        autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
            val selectedWorkout = workoutMap[parent.getItemAtPosition(position)]
            if (selectedWorkout != null)
                workoutId = selectedWorkout
        }
        val convertedDate = convertDateFormat(selectedDate)
        addButton.setOnClickListener {
            val db = DatabaseProvider.getDatabase(requireActivity())
            lifecycleScope.launch {
                val trainingPlan = TrainingPlan(
                    0,
                    convertedDate,
                    workoutId,
                    userId
                )
                db.trainingPlanDao.insertTrainingPlan(trainingPlan)
                Toast.makeText(requireContext(), "Training plan added :)", Toast.LENGTH_SHORT).show()

                val workoutDate = stringToCalendar(convertedDate)?.apply {
                    set(Calendar.HOUR_OF_DAY, 9)
                    set(Calendar.MINUTE, 0)
                }

                val workout = db.workoutDao.getWorkoutById(workoutId)

                if (workoutDate != null) {
                    scheduleWorkoutNotification(requireContext(), workoutDate, workout.name)
                }
            }
            (activity as AddTrainingPlanActivity).finish()
        }

        return view
    }

    private fun convertDateFormat(inputDate: String): String {
        val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val date = inputFormat.parse(inputDate)

        return outputFormat.format(date as Date)
    }

    @SuppressLint("ScheduleExactAlarm")
    fun scheduleWorkoutNotification(context: Context, workoutDate: Calendar, workoutName: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra("WORKOUT_NAME", workoutName)
        }
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            workoutDate.timeInMillis,
            pendingIntent
        )
    }

    fun stringToCalendar(dateString: String): Calendar? {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = format.parse(dateString)
        return if (date != null) {
            Calendar.getInstance().apply { time = date }
        } else {
            null
        }
    }
}