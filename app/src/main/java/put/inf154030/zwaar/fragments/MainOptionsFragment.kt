package put.inf154030.zwaar.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import put.inf154030.zwaar.R
import put.inf154030.zwaar.activities.AllWorkoutsActivity
import put.inf154030.zwaar.activities.TodaysWorkoutActivity
import put.inf154030.zwaar.activities.TrainingPlanActivity
import put.inf154030.zwaar.activities.YourProgressActivity

class MainOptionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_options, container, false)

        val todaysWorkout = view.findViewById<CardView>(R.id.card_view_todays_workout)
        val yourProgress = view.findViewById<CardView>(R.id.card_view_your_progress)
        val trainingPlan = view.findViewById<CardView>(R.id.card_view_training_plan)
        val allWorkouts = view.findViewById<CardView>(R.id.card_view_all_workouts)

        todaysWorkout.setOnClickListener {
            val intent = Intent(requireActivity(), TodaysWorkoutActivity::class.java)
            startActivity(intent)
        }

        yourProgress.setOnClickListener {
            val intent = Intent(requireActivity(), YourProgressActivity::class.java)
            startActivity(intent)
        }

        trainingPlan.setOnClickListener {
            val intent = Intent(requireActivity(), TrainingPlanActivity::class.java)
            startActivity(intent)
        }

        allWorkouts.setOnClickListener {
            val intent = Intent(requireActivity(), AllWorkoutsActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}