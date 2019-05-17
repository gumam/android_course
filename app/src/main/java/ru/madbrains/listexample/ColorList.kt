package ru.madbrains.listexample

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter(private val colors: List<String>) : RecyclerView.Adapter<ColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val textView = TextView(parent.context)
        textView.textSize = 24f
        textView.setPadding(32, 12, 32, 12)

        return ColorViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return colors.size
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors.get(position))
    }
}

class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(color: String) {
        (itemView as TextView).text = color
    }
}