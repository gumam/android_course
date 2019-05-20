package ru.madbrains.listexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CatAdapter(private val catTexts: List<Cat>) : RecyclerView.Adapter<CatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cat_item, parent, false)

        return CatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return catTexts.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(catTexts.get(position))
    }
}

class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageView = itemView.findViewById<ImageView>(R.id.imageViewId)
    private val textView = itemView.findViewById<TextView>(R.id.textViewId)

    fun bind(cat: Cat) {
        textView.text = cat.text
        Glide.with(itemView).load(cat.image).into(imageView)
    }
}