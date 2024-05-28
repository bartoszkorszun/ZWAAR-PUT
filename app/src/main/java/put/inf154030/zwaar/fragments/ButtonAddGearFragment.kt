package put.inf154030.zwaar.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import put.inf154030.zwaar.R
import put.inf154030.zwaar.activities.MyGearActivity

class ButtonAddGearFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_button_add_gear, container, false)

        val buttonAddGear = view.findViewById<Button>(R.id.button_add_gear)

        buttonAddGear.setOnClickListener {
            val intent = Intent(requireActivity(), MyGearActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}