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
import kotlinx.coroutines.withContext
import put.inf154030.zwaar.MainActivity
import put.inf154030.zwaar.R
import put.inf154030.zwaar.activities.HomeScreenActivity
import put.inf154030.zwaar.activities.LogInActivity
import put.inf154030.zwaar.database.DatabaseProvider

class ButtonLogInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_button_log_in, container, false)

        val buttonLogIn = view.findViewById<Button>(R.id.button_log_in)
        buttonLogIn.setOnClickListener() {
            if (activity is MainActivity) {
                val intent = Intent(requireActivity(), LogInActivity::class.java)
                startActivity(intent)
            }
            if (activity is LogInActivity) {

                val logInFragment = requireActivity()
                    .supportFragmentManager
                    .findFragmentById(R.id.fragment_container_fields) as FieldsLogInFragment

                val login = logInFragment.getLogin()
                val password = logInFragment.getPassword()

                lifecycleScope.launch {
                    val isPasswordCorrect = checkPassword(login, password)

                    if (isPasswordCorrect) {
                        val db = DatabaseProvider.getDatabase(activity as LogInActivity)
                        println(db.userDao.getUserId(login))

                        val intent = Intent(requireActivity(), HomeScreenActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText((activity as LogInActivity), "Password is incorrect!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return view
    }

    private suspend fun checkPassword(login: String, password: String): Boolean {
        val db = DatabaseProvider.getDatabase(activity as LogInActivity)
        var correctPassword: String? = null

        withContext(Dispatchers.IO) {
            correctPassword = db.userDao.getUserPassword(login)
        }

        return password == correctPassword
    }
}