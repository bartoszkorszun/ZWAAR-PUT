package put.inf154030.zwaar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import put.inf154030.zwaar.R
import put.inf154030.zwaar.entities.Exercise

class ExerciseAdapter(
    private val exerciseList: List<Exercise>,
    private val onItemClick: (Exercise) -> Unit
): RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(exerciseList[position])
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val exerciseNameTextView: TextView = itemView.findViewById(R.id.text_view_item_name)

        fun bind(exercise: Exercise) {
            exerciseNameTextView.text = exercise.name
            itemView.setOnClickListener {
                onItemClick(exercise)
            }
        }
    }
}