package put.inf154030.zwaar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import put.inf154030.zwaar.R

class EmptyItemAdapter(): RecyclerView.Adapter<EmptyItemAdapter.EmptyItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.empty_item, parent, false)
        return EmptyItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmptyItemViewHolder, position: Int) {
        holder.itemTextNoTraining
        holder.itemTextPressToAdd
    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class EmptyItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemTextNoTraining: TextView = view.findViewById(R.id.text_view_no_training)
        val itemTextPressToAdd: TextView = view.findViewById(R.id.text_view_press_to_add)
    }
}