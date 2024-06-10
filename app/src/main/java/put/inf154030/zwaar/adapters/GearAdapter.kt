package put.inf154030.zwaar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import put.inf154030.zwaar.R
import put.inf154030.zwaar.entities.Gear

class GearAdapter(
    private val gearList: List<Gear>,
    private val onItemClick: (Gear) -> Unit
): RecyclerView.Adapter<GearAdapter.GearViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GearViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return GearViewHolder(view)
    }

    override fun onBindViewHolder(holder: GearViewHolder, position: Int) {
        holder.bind(gearList[position])
    }

    override fun getItemCount(): Int {
        return gearList.size
    }

    inner class GearViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val gearNameTextView: TextView = itemView.findViewById(R.id.text_view_item_name)

        fun bind(gear: Gear) {
            gearNameTextView.text = gear.name
            itemView.setOnClickListener {
                onItemClick(gear)
            }
        }
    }
}