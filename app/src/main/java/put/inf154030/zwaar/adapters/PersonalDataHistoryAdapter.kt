package put.inf154030.zwaar.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import put.inf154030.zwaar.R
import put.inf154030.zwaar.relations.PersonalDataHistory

class PersonalDataHistoryAdapter(
    private val personalDataHistoryList: List<PersonalDataHistory>
): RecyclerView.Adapter<PersonalDataHistoryAdapter.PersonalDataHistoryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonalDataHistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_personal_data_history, parent, false)
        return PersonalDataHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonalDataHistoryViewHolder, position: Int) {
        holder.bind(personalDataHistoryList[position])
    }

    override fun getItemCount(): Int {
        return personalDataHistoryList.size
    }

    inner class PersonalDataHistoryViewHolder(intemView: View) : RecyclerView.ViewHolder(intemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.text_view_date)
        private val weightTextView: TextView = itemView.findViewById(R.id.text_view_weight)

        @SuppressLint("SetTextI18n")
        fun bind(personalDataHistory: PersonalDataHistory) {
            dateTextView.text = personalDataHistory.date
            weightTextView.text = personalDataHistory.weight.toString() + "kg"
        }
    }
}