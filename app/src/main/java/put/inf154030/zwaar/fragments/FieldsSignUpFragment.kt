package put.inf154030.zwaar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import put.inf154030.zwaar.R

class FieldsSignUpFragment : Fragment() {

    private lateinit var loginField: EditText
    private lateinit var passwordField: EditText
    private lateinit var emailField: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fields_sign_up, container, false)

        loginField = view.findViewById(R.id.edit_text_login)
        passwordField = view.findViewById(R.id.edit_text_password)
        emailField = view.findViewById(R.id.edit_text_email)

        return view
    }

    fun getLogin(): String {
        return loginField.text.toString()
    }

    fun getPassword(): String {
        return passwordField.text.toString()
    }

    fun getEmail(): String {
        return emailField.text.toString()
    }
}