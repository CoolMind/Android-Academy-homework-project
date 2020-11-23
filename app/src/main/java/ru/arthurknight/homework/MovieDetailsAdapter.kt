package ru.arthurknight.homework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView

class MovieDetailsAdapter : RecyclerView.Adapter<MovieDetailsAdapter.ViewHolder>() {

    private val items: MutableList<Item> = mutableListOf()

    init {
        setHasStableIds(true)
    }

    fun setItems(list: List<Item>) {
        items.clear()
        notifyDataSetChanged()
        items.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_movie_actor,
            parent, false
        )
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder) {
            photo.setImageResource(item.photo)
            name.text = item.name
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.photo)
        val name: TextView = itemView.findViewById(R.id.name)
    }

    data class Item(
        @DrawableRes val photo: Int,
        val name: String
    )

}