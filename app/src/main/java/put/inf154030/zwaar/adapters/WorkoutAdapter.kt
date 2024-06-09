package put.inf154030.zwaar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import put.inf154030.zwaar.R
import put.inf154030.zwaar.entities.Workout

class WorkoutAdapter(
    private val workoutList: List<Workout>,
    private val onItemClick: (Workout) -> Unit
): RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        holder.bind(workoutList[position])
    }

    override fun getItemCount(): Int {
        return workoutList.size
    }

    inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val workoutNameTextView: TextView = itemView.findViewById(R.id.text_view_item_name)

        fun bind(workout: Workout) {
            workoutNameTextView.text = workout.name
            itemView.setOnClickListener {
                onItemClick(workout)
            }
        }
    }
}