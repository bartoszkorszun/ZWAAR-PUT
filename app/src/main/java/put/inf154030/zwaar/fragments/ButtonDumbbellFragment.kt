package put.inf154030.zwaar.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import put.inf154030.zwaar.R
import put.inf154030.zwaar.activities.HomeScreenActivity

class ButtonDumbbellFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_button_dumbbell, container, false)

        val buttonDumbbell = view.findViewById<ImageButton>(R.id.image_button_dumbbell)
        buttonDumbbell.setOnClickListener {
            val intent = Intent(requireActivity(), HomeScreenActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }

        return view
    }
}