package ru.madbrains.listexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CatAdapter(private val cats: List<Cat>) : RecyclerView.Adapter<CatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        //создаем view из файла интерфейса
        val rootView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.cat_item, parent, false)
        return CatViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return cats.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(cats.get(position))
    }
}

class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //находим ImageView
    private val imageView: ImageView = itemView.findViewById(R.id.imageViewId)

    //Находим TextView
    private val textView: TextView = itemView.findViewById(R.id.textViewId)

    fun bind(cat: Cat) {
        textView.text = cat.text //загружаем текст в TextView

        Glide.with(itemView).load(cat.image).into(imageView)

        itemView.setOnClickListener {
            DetailActivity.openDetailActivity(itemView.context, cat.text)
        }
    }
}