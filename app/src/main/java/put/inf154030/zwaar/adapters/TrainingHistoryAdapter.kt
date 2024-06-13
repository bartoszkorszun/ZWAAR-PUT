package put.inf154030.zwaar.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import put.inf154030.zwaar.Converters
import put.inf154030.zwaar.R
import put.inf154030.zwaar.database.DatabaseProvider
import put.inf154030.zwaar.relations.TrainingDataHistory

class TrainingHistoryAdapter(
    private val trainingDataHistoryList: List<TrainingDataHistory>,
    private val context: Context
): RecyclerView.Adapter<TrainingHistoryAdapter.TrainingHistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingHistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.training_history_item, parent, false)
        return TrainingHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrainingHistoryViewHolder, position: Int) {
        holder.bind(trainingDataHistoryList[position])
    }

    override fun getItemCount(): Int {
        return trainingDataHistoryList.size
    }

    inner class TrainingHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewWorkoutName = itemView.findViewById<TextView>(R.id.text_view_workout_name)
        private val workoutExerciseList = itemView.findViewById<RecyclerView>(R.id.recycler_view_workouts_exercise_list)
        private val exerciseAdapter = TrainingHistoryExerciseAdapter(emptyList(), context)

        init {
            workoutExerciseList.layoutManager = LinearLayoutManager(context)
            workoutExerciseList.adapter = exerciseAdapter
        }

        @SuppressLint("SetTextI18n")
        fun bind(trainingDataHistory: TrainingDataHistory) {
            val db = DatabaseProvider.getDatabase(context)
            textViewWorkoutName.text = "${trainingDataHistory.date} - ${trainingDataHistory.name}"

            val workoutExerciseIds = Converters().toIntList(trainingDataHistory.workoutExerciseIds)
            CoroutineScope(Dispatchers.IO).launch {
                val workoutExercises = db.workoutExerciseDao.getWorkoutExerciseByIds(workoutExerciseIds)
                withContext(Dispatchers.Main) {
                    exerciseAdapter.updateData(workoutExercises)
                }
            }
        }
    }
}