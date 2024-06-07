package put.inf154030.zwaar.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import put.inf154030.zwaar.R
import put.inf154030.zwaar.UserSession
import put.inf154030.zwaar.activities.LogInActivity
import put.inf154030.zwaar.activities.ProfileActivity
import put.inf154030.zwaar.database.DatabaseProvider

class ButtonSaveChangesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_button_save_changes, container, false)

        val buttonSaveChanges = view.findViewById<Button>(R.id.button_save_changes)

        buttonSaveChanges.setOnClickListener {

            val profileActivity = (activity as ProfileActivity)

            val gender = profileActivity.getGender()
            val height = profileActivity.getHeight().toDouble()
            val weight = profileActivity.getWeight().toDouble()
            val bmi = calculateBmi(height/100, weight)

            lifecycleScope.launch {
                val userId = UserSession.loggedInUserId
                updateUser(userId, gender, height, weight, bmi)
            }

            profileActivity.setBMI(bmi)
        }

        return view
    }

    @SuppressLint("DefaultLocale")
    private fun calculateBmi(height: Double, weight: Double): Double {
        val bmi = weight / (height * height)
        return String.format("%.1f", bmi).toDouble()
    }

    private suspend fun updateUser(
        userId: Int,
        gender: String,
        height: Double,
        weight: Double,
        bmi: Double
        ) {
        withContext(Dispatchers.IO) {
            val db = DatabaseProvider.getDatabase(activity as ProfileActivity)
            val user = db.userDao.getUserById(userId)
            val updatedUser = user?.let {
                user.copy(
                    userId,
                    it.login,
                    it.password,
                    it.email,
                    gender,
                    height,
                    weight,
                    bmi
                )
            }
            if (updatedUser != null) {
                db.userDao.updateUser(updatedUser)
                Toast.makeText((activity as ProfileActivity), "Saved", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText((activity as ProfileActivity), "Oops! Something went wrong. Try again", Toast.LENGTH_LONG).show()
        }
    }
}