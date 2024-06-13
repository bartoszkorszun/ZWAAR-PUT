package put.inf154030.zwaar.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import put.inf154030.zwaar.R
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.relations.WorkoutExercise

class TrainingHistoryExerciseAdapter(
    private var workoutExerciseList: List<WorkoutExercise>,
    private val context: Context
): RecyclerView.Adapter<TrainingHistoryExerciseAdapter.TrainingHistoryExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingHistoryExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_item_no_checkbox, parent, false)
        return TrainingHistoryExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrainingHistoryExerciseViewHolder, position: Int) {
        holder.bind(workoutExerciseList[position])
    }

    override fun getItemCount(): Int {
        return workoutExerciseList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newWorkoutExerciseList: List<WorkoutExercise>) {
        workoutExerciseList = newWorkoutExerciseList
        notifyDataSetChanged()
    }

    inner class TrainingHistoryExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewExerciseName = itemView.findViewById<TextView>(R.id.text_view_exercise_name)
        private val textViewData = itemView.findViewById<TextView>(R.id.text_view_data)

        @SuppressLint("SetTextI18n")
        fun bind(workoutExercise: WorkoutExercise) {
            val db = DatabaseProvider.getDatabase(context)
            CoroutineScope(Dispatchers.IO).launch {
                val exercise = db.exerciseDao.getExerciseById(workoutExercise.exerciseId)
                withContext(Dispatchers.Main) {
                    textViewExerciseName.text = exercise.name
                    textViewData.text =
                        "${workoutExercise.series} x ${workoutExercise.quantity} x ${workoutExercise.weight}kg"
                    println("${textViewExerciseName.text} ... ${textViewData.text}")
                }
            }
        }
    }
}