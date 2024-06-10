package put.inf154030.zwaar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import put.inf154030.zwaar.R
import put.inf154030.zwaar.UserSession
import put.inf154030.zwaar.activities.AddGearActivity
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.relations.UserGear

class AddGearFragment : Fragment() {

    private lateinit var gearMap: Map<String, Int>
    var gearId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_gear, container, false)
        val autoCompleteTextView = view.findViewById<AutoCompleteTextView>(R.id.auto_complete_select_gear)
        val addButton = view.findViewById<Button>(R.id.button_add)

        lifecycleScope.launch {
            val db = DatabaseProvider.getDatabase(requireActivity())
            val gearList = db.gearDao.getAll()
            val gearNames = gearList.map { it.name }
            gearMap = gearList.associateBy({ it.name }, { it.gearId })
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, gearNames)
            autoCompleteTextView.setAdapter(adapter)
        }

        autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
            val selectedGear = gearMap[parent.getItemAtPosition(position)]
            if (selectedGear != null)
                gearId = selectedGear
        }

        addButton.setOnClickListener {
            val db = DatabaseProvider.getDatabase(requireActivity())
            lifecycleScope.launch {
                val userGear = UserGear(
                    0,
                    UserSession.loggedInUserId,
                    gearId
                )
                db.userGearDao.insertUserGear(userGear)
                Toast.makeText(requireContext(), "Gear added :)", Toast.LENGTH_SHORT).show()
            }
            (activity as AddGearActivity).finish()
        }

        return view
    }
}