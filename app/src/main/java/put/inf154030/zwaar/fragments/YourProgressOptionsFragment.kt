package put.inf154030.zwaar.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import put.inf154030.zwaar.R
import put.inf154030.zwaar.activities.PersonalDataHistoryActivity
import put.inf154030.zwaar.activities.TrainingHistoryActivity

class YourProgressOptionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_your_progress_options, container, false)

        val trainingHistory = view.findViewById<CardView>(R.id.card_view_training_history)
        val personalDataHistory = view.findViewById<CardView>(R.id.card_view_personal_data_history)

        trainingHistory.setOnClickListener {
            val intent = Intent(requireActivity(), TrainingHistoryActivity::class.java)
            startActivity(intent)
        }

        personalDataHistory.setOnClickListener {
            val intent = Intent(requireActivity(), PersonalDataHistoryActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}