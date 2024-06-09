package put.inf154030.zwaar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import put.inf154030.zwaar.R
import put.inf154030.zwaar.activities.AddExerciseActivity
import put.inf154030.zwaar.activities.AddWorkoutActivity
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.relations.WorkoutExercise

class AddExerciseFragment : Fragment() {

    private lateinit var exerciseMap: Map<String, Int>
    private var exerciseId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_exercise, container, false)

        val autoCompleteTextView = view.findViewById<AutoCompleteTextView>(R.id.auto_complete_select_exercise)

        lifecycleScope.launch {
            val db = DatabaseProvider.getDatabase(requireActivity())
            val exercises = db.exerciseDao.getAll()
            val exerciseNames = exercises.map { it.name }
            exerciseMap = exercises.associateBy({ it.name }, {it.exerciseId})
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, exerciseNames)
            autoCompleteTextView.setAdapter(adapter)
        }

        autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
            val selectedExerciseId = exerciseMap[parent.getItemAtPosition(position) as String]
            if (selectedExerciseId != null) {
                exerciseId = selectedExerciseId
            }
        }

        val weight = view.findViewById<EditText>(R.id.edit_text_add_weight)
        val quantity = view.findViewById<EditText>(R.id.edit_text_add_quantity)
        val series = view.findViewById<EditText>(R.id.edit_text_add_series)
        val addButton = view.findViewById<Button>(R.id.button_add)

        addButton.setOnClickListener {
            lifecycleScope.launch {
                val db = DatabaseProvider.getDatabase(requireActivity())
                val workoutExercise = WorkoutExercise(
                    0,
                    workoutId = (activity as AddExerciseActivity).getWorkoutId(),
                    exerciseId,
                    weight.text.toString().toDouble(),
                    quantity.text.toString().toInt(),
                    series.text.toString().toInt()
                )
                db.workoutExerciseDao.insertWorkoutExercise(workoutExercise)
            }
        }

        return view
    }
}