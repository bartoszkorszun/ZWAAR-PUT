package put.inf154030.zwaar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import put.inf154030.zwaar.R
import put.inf154030.zwaar.UserSession
import put.inf154030.zwaar.activities.AddWorkoutActivity
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.entities.Workout

class AddWorkoutFragment : Fragment() {

    private val db = DatabaseProvider.getDatabase(requireActivity())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_workout, container, false)

        val editTextWorkoutName = view.findViewById<EditText>(R.id.edit_text_enter_name)
        val buttonAdd = view.findViewById<Button>(R.id.button_add)

        buttonAdd.setOnClickListener {
            lifecycleScope.launch {
                val workout = Workout(
                    0,
                    editTextWorkoutName.text.toString(),
                    UserSession.loggedInUserId
                )
                db.workoutDao.insertWorkout(workout)
                Toast.makeText((activity as AddWorkoutActivity), "Workout created :)", Toast.LENGTH_SHORT).show()
            }
            (activity as AddWorkoutActivity).finish()
        }
        return view
    }
}