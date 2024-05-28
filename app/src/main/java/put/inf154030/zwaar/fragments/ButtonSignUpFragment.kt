package put.inf154030.zwaar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import put.inf154030.zwaar.MainActivity
import put.inf154030.zwaar.R
import put.inf154030.zwaar.activities.SignUpActivity

class ButtonSignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_button_sign_up, container, false)

        val buttonSignUp = view.findViewById<Button>(R.id.button_sign_up)
        buttonSignUp.setOnClickListener {
            if (activity is MainActivity)
                (activity as MainActivity).startSignUpActivity()
            if (activity is SignUpActivity)
                (activity as SignUpActivity).startHomeScreenActivity()
        }

        return view
    }
}