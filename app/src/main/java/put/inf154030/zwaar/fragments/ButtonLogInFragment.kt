package put.inf154030.zwaar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import put.inf154030.zwaar.MainActivity
import put.inf154030.zwaar.R
import put.inf154030.zwaar.activities.LogInActivity

class ButtonLogInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_button_log_in, container, false)

        val buttonLogIn = view.findViewById<Button>(R.id.button_log_in)
        buttonLogIn.setOnClickListener() {
            if (activity is MainActivity)
                (activity as MainActivity).startLogInActivity()
            if (activity is LogInActivity)
                (activity as LogInActivity).startHomeScreenActivity()
        }

        return view
    }
}