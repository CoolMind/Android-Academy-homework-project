package ru.arthurknight.homework.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import ru.arthurknight.homework.R

class MoviesDetailsAdapter : RecyclerView.Adapter<MoviesDetailsAdapter.ViewHolder>() {

    private val items: MutableList<Item> = mutableListOf()

    init {
        setHasStableIds(true)
    }

    fun setItems(list: List<Item>) {
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
            photo.setImageResource(item.photo)
            name.text = item.name
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long = items[position].id

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.photo)
        val name: TextView = itemView.findViewById(R.id.name)
    }

    data class Item(
        val id: Long,
        @DrawableRes val photo: Int,
        val name: String
    )

}