package put.inf154030.zwaar.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import put.inf154030.zwaar.R
import put.inf154030.zwaar.activities.AddExerciseActivity
import put.inf154030.zwaar.activities.AddGearActivity
import put.inf154030.zwaar.activities.AddTrainingPlanActivity
import put.inf154030.zwaar.activities.AddWorkoutActivity
import put.inf154030.zwaar.activities.AllWorkoutsActivity
import put.inf154030.zwaar.activities.MyGearActivity
import put.inf154030.zwaar.activities.TrainingPlanActivity
import put.inf154030.zwaar.activities.WorkoutActivity

class ButtonAddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_button_add, container, false)

        val addButton = view.findViewById<ImageButton>(R.id.image_button_add)

        addButton.setOnClickListener {
            if (activity is AllWorkoutsActivity) {
                val intent = Intent(activity as AllWorkoutsActivity, AddWorkoutActivity::class.java)
                startActivity(intent)
            }
            if (activity is WorkoutActivity) {
                val intent = Intent(activity as WorkoutActivity, AddExerciseActivity::class.java)
                intent.putExtra("workout_id", (activity as WorkoutActivity).getWorkoutId())
                startActivity(intent)
            }
            if (activity is MyGearActivity) {
                val intent = Intent(activity as MyGearActivity, AddGearActivity::class.java)
                startActivity(intent)
            }
            if (activity is TrainingPlanActivity) {
                val intent = Intent(activity as TrainingPlanActivity, AddTrainingPlanActivity::class.java)
                intent.putExtra("selected_date", (activity as TrainingPlanActivity).getSelectedDate())
                startActivity(intent)
            }
        }

        return view
    }
}