package put.inf154030.zwaar.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import put.inf154030.zwaar.MainActivity
import put.inf154030.zwaar.R
import put.inf154030.zwaar.UserSession
import put.inf154030.zwaar.activities.HomeScreenActivity
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
                val db = DatabaseProvider.getDatabase(requireActivity())
                val signUpFragment = requireActivity()
                    .supportFragmentManager
                    .findFragmentById(R.id.fragment_container_fields) as FieldsSignUpFragment

                val login = signUpFragment.getLogin()
                val password = signUpFragment.getPassword()
                val email = signUpFragment.getEmail()
                val isCheckboxChecked = (activity as SignUpActivity).getCheckBoxState()

                if (login.isEmpty() || password.isEmpty()) {
                    Toast.makeText((activity as SignUpActivity), "Fill all of the fields!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (isCheckboxChecked) {
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

                    lifecycleScope.launch {
                        val userExists = db.userDao.getUserByLogin(user.login)
                        if (userExists == null) {
                            if (isValidEmail(user.email)) {
                                db.userDao.inserUser(user)
                                UserSession.loggedInUserId = db.userDao.getUserId(user.login)
                                val intent = Intent(requireActivity(), HomeScreenActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                startActivity(intent)
                                (activity as SignUpActivity).finish()
                            } else {
                                Toast.makeText((activity as SignUpActivity), "Incorrect e-mail address", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText((activity as SignUpActivity), "This user already exists!", Toast.LENGTH_SHORT).show()
                        }
                    }

                } else {
                    Toast.makeText((activity as SignUpActivity), "Please agree to the privacy policy :)", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return view
    }

    private fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}