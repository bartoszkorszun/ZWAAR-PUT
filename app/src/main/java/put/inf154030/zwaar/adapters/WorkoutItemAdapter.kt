package put.inf154030.zwaar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import put.inf154030.zwaar.R
import put.inf154030.zwaar.entities.Workout

class WorkoutItemAdapter(private val workout: Workout?): RecyclerView.Adapter<WorkoutItemAdapter.WorkoutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        workout?.let {
            holder.workoutName.text = it.name
        }
    }

    override fun getItemCount(): Int {
        return if (workout != null) 1 else 0
    }

    inner class WorkoutViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val workoutName: TextView = view.findViewById(R.id.text_view_item_name)
    }
}