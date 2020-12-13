package ru.arthurknight.homework.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.arthurknight.homework.R

class MovieActorsAdapter : RecyclerView.Adapter<MovieActorsAdapter.ViewHolder>() {

    private val items: MutableList<Actor> = mutableListOf()

    fun setItems(list: List<Actor>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.view_holder_actor,
            parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder) {
            Glide.with(photo)
                .load(item.photoUrl)
                .into(photo)
            name.text = item.name
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long = items[position].id.toLong()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.photo)
        val name: TextView = itemView.findViewById(R.id.name)
    }

    data class Actor(
        val id: Int,
        val photoUrl: String,
        val name: String
    )

}