package put.inf154030.zwaar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import put.inf154030.zwaar.R
import put.inf154030.zwaar.activities.AddExerciseActivity
import put.inf154030.zwaar.activities.AddWorkoutActivity
import put.inf154030.zwaar.activities.ProfileActivity
import put.inf154030.zwaar.database.Database
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.relations.WorkoutExercise

class AddExerciseFragment : Fragment() {

    private lateinit var exerciseMap: Map<String, Int>
    private var exerciseId: Int = -1
    private var workoutId: Int = -1
    private var isPassedFromActivity: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_exercise, container, false)
        val autoCompleteTextView = view.findViewById<AutoCompleteTextView>(R.id.auto_complete_select_exercise)
        val weight = view.findViewById<EditText>(R.id.edit_text_add_weight)
        val quantity = view.findViewById<EditText>(R.id.edit_text_add_quantity)
        val series = view.findViewById<EditText>(R.id.edit_text_add_series)
        val addButton = view.findViewById<Button>(R.id.button_add)

        exerciseId = (activity as AddExerciseActivity).getExerciseId()
        workoutId = (activity as AddExerciseActivity).getWorkoutId()

        lifecycleScope.launch {
            val db = DatabaseProvider.getDatabase(requireActivity())
            val exercises = db.exerciseDao.getAll()
            val exerciseNames = exercises.map { it.name }
            exerciseMap = exercises.associateBy({ it.name }, {it.exerciseId})
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, exerciseNames)
            autoCompleteTextView.setAdapter(adapter)

            if (exerciseId != 0) {
                isPassedFromActivity = true
                val selectedExerciseName = exercises.find { it.exerciseId == exerciseId }?.name
                autoCompleteTextView.setText(selectedExerciseName, false)
                weight.setText(db.workoutExerciseDao.getWeight(workoutId, exerciseId).toString())
                quantity.setText(db.workoutExerciseDao.getQuantity(workoutId, exerciseId).toString())
                series.setText(db.workoutExerciseDao.getSeries(workoutId, exerciseId).toString())
            }
        }

        autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
            val selectedExerciseId = exerciseMap[parent.getItemAtPosition(position) as String]
            if (selectedExerciseId != null)
                exerciseId = selectedExerciseId
        }

        addButton.setOnClickListener {
            if (isPassedFromActivity) {
                lifecycleScope.launch {
                    val db = DatabaseProvider.getDatabase(requireActivity())
                    val workoutExercise = db.workoutExerciseDao.getWorkoutExerciseByIds(workoutId, exerciseId)
                    val updatedWorkoutExercise = workoutExercise?.let {
                        workoutExercise.copy(
                            workoutExercise.id,
                            workoutId,
                            exerciseId,
                            weight.text.toString().toDouble(),
                            quantity.text.toString().toInt(),
                            series.text.toString().toInt()
                        )
                    }
                    if (updatedWorkoutExercise != null) {
                        db.workoutExerciseDao.updateWorkoutExercise(updatedWorkoutExercise)
                        Toast.makeText((activity as AddExerciseActivity), "Exercise updated :)", Toast.LENGTH_SHORT).show()
                    }
                    else
                        Toast.makeText((activity as AddExerciseActivity), "Oops! Something went wrong. Try again.", Toast.LENGTH_SHORT).show()
                }
            } else {
                lifecycleScope.launch {
                    val db = DatabaseProvider.getDatabase(requireActivity())
                    val workoutExercise = WorkoutExercise(
                        0,
                        workoutId,
                        exerciseId,
                        weight.text.toString().toDouble(),
                        quantity.text.toString().toInt(),
                        series.text.toString().toInt()
                    )
                    db.workoutExerciseDao.insertWorkoutExercise(workoutExercise)
                    Toast.makeText((activity as AddExerciseActivity), "Exercise added :)", Toast.LENGTH_SHORT).show()
                }
            }
            (activity as AddExerciseActivity).finish()
        }

        return view
    }
}