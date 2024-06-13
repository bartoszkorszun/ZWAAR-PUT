package put.inf154030.zwaar.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import put.inf154030.zwaar.R
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.relations.WorkoutExercise

class TodaysWorkoutAdapter(
    private val workoutExerciseList: List<WorkoutExercise>,
    private val context: Context,
    private val checkboxChange: (WorkoutExercise, Boolean) -> Unit
): RecyclerView.Adapter<TodaysWorkoutAdapter.TodaysWorkoutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodaysWorkoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
        return TodaysWorkoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodaysWorkoutViewHolder, position: Int) {
        holder.bind(workoutExerciseList[position])
    }

    override fun getItemCount(): Int {
        return workoutExerciseList.size
    }

    inner class TodaysWorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewExerciseName = itemView.findViewById<TextView>(R.id.text_view_exercise_name)
        private val textViewData = itemView.findViewById<TextView>(R.id.text_view_data)
        private val checkboxDone = itemView.findViewById<CheckBox>(R.id.checkbox_done)

        @SuppressLint("SetTextI18n")
        fun bind(workoutExercise: WorkoutExercise) {
            val db = DatabaseProvider.getDatabase(context)
            CoroutineScope(Dispatchers.IO).launch {
                val exercise = db.exerciseDao.getExerciseById(workoutExercise.exerciseId)

                withContext(Dispatchers.Main) {
                    textViewExerciseName.text = exercise.name
                    textViewData.text =
                        "${workoutExercise.series} x ${workoutExercise.quantity} x ${workoutExercise.weight}kg"
                    checkboxDone.setOnCheckedChangeListener { _, isChecked ->
                        checkboxChange(workoutExercise, isChecked)
                    }
                }
            }
        }
    }
}