package put.inf154030.zwaar.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import put.inf154030.zwaar.MainActivity
import put.inf154030.zwaar.R
import put.inf154030.zwaar.activities.HomeScreenActivity
import put.inf154030.zwaar.activities.LogInActivity
import put.inf154030.zwaar.activities.ProfileActivity
import put.inf154030.zwaar.activities.SignUpActivity
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.entities.User

class ButtonSignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_button_sign_up, container, false)

        val buttonSignUp = view.findViewById<Button>(R.id.button_sign_up)
        buttonSignUp.setOnClickListener {
            if (activity is MainActivity){
                val intent = Intent(requireActivity(), SignUpActivity::class.java)
                startActivity(intent)
            }
            if (activity is SignUpActivity){

                val signUpFragment = requireActivity()
                    .supportFragmentManager
                    .findFragmentById(R.id.fragment_container_fields) as FieldsSignUpFragment

                val login = signUpFragment.getLogin()
                val password = signUpFragment.getPassword()
                val email = signUpFragment.getEmail()
                val isCheckboxChecked = (activity as SignUpActivity).getCheckBoxState()

                if (isCheckboxChecked) {
                    val db = DatabaseProvider.getDatabase(activity as SignUpActivity)

                    val user = User(
                        userId = 0,
                        login = login,
                        password = password,
                        email = email,
                        gender = null,
                        height = null,
                        weight = null,
                        bmi = null
                    )

                    lifecycleScope.launch(Dispatchers.IO) {
                        db.userDao.inserUser(user)
                    }

                    val intent = Intent(requireActivity(), HomeScreenActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText((activity as SignUpActivity), "Please agree to the privacy policy :)", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }
}