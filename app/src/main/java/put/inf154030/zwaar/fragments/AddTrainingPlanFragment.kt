package put.inf154030.zwaar.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
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
import put.inf154030.zwaar.R
import put.inf154030.zwaar.UserSession
import put.inf154030.zwaar.activities.AddTrainingPlanActivity
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.entities.TrainingPlan
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddTrainingPlanFragment : Fragment() {

    private lateinit var editTextDate: EditText
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

        editTextDate = view.findViewById(R.id.edit_text_date)
        val currentDate = getCurrentDate()
        editTextDate.setText(currentDate)

        editTextDate.setCompoundDrawablesWithIntrinsicBounds(
            null, null, getDrawable(requireContext(), R.drawable.ic_action_date), null
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

        addButton.setOnClickListener {
            val db = DatabaseProvider.getDatabase(requireActivity())
            lifecycleScope.launch {
                val trainingPlan = TrainingPlan(
                    0,
                    convertDateFormat(editTextDate.text.toString()),
                    workoutId,
                    userId
                )
                db.trainingPlanDao.insertTrainingPlan(trainingPlan)
                Toast.makeText(requireContext(), "Training plan added :)", Toast.LENGTH_SHORT).show()
            }
            (activity as AddTrainingPlanActivity).finish()
        }

        return view
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

        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
            editTextDate.setText(selectedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun convertDateFormat(inputDate: String): String {
        val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val date = inputFormat.parse(inputDate)

        return outputFormat.format(date as Date)
    }
}