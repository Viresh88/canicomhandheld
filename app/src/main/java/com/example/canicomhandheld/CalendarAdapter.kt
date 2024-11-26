package com.example.canicomhandheld


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CalendarAdapter(
    private val context: Context,
    private val daysList: List<String>,
    private val today: Int, // Current day
    private val isCurrentMonthAndYear: Boolean, // Flag for current month and year
    private val onDateClick: (String) -> Unit
) : RecyclerView.Adapter<CalendarAdapter.DayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_calendar_day, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = daysList[position]
        holder.dayText.text = day

        // Highlight the current day if it matches and is the current month and year
        if (isCurrentMonthAndYear && day.isNotEmpty() && day.toInt() == today) {
            holder.dayText.setBackgroundResource(R.drawable.current_day_background)
        } else {
            holder.dayText.setBackgroundResource(0) // Remove any background if not current day
        }

        // Set click listener
        holder.dayText.setOnClickListener {
            onDateClick(day)
        }
    }

    override fun getItemCount(): Int = daysList.size

    inner class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayText: TextView = itemView.findViewById(R.id.dayTextView)
    }
}

