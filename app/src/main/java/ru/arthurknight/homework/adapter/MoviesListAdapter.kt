package ru.arthurknight.homework.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.arthurknight.homework.R
import ru.arthurknight.homework.util.DrawableUtil.setSvgColor

class MoviesListAdapter : RecyclerView.Adapter<MoviesListAdapter.AbstractViewHolder>() {

    private var listener: ItemClickListener? = null
    private val diffCallback = DiffUtilCallback()
    private var items = mutableListOf<AbstractItem>()


    fun setClickListener(listener: ItemClickListener?) {
        this.listener = listener
    }

    fun isHeader(position: Int): Boolean = getItemViewType(position) == ViewType.HEADER.ordinal

    fun getItem(id: Int): AbstractItem = items.first { it.id == id }

    fun setItem(item: AbstractItem) {
        val index = items.indexOfFirst { it.id == item.id }
        if (index != -1) {
            items[index] = item
            notifyItemChanged(index)
        }
    }

    fun setItems(values: List<AbstractItem>) {
        diffCallback.setItems(items, values)
        val chatsDiffResult = DiffUtil.calculateDiff(diffCallback)

        chatsDiffResult.dispatchUpdatesTo(this)

        items.clear()
        items.addAll(values as MutableList<AbstractItem>)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        val layout = when (viewType) {
            ViewType.HEADER.ordinal -> R.layout.view_holder_movies_header
            ViewType.MOVIE.ordinal -> R.layout.view_holder_movie
            else -> throw IllegalStateException("Unknown view type $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return when (viewType) {
            ViewType.HEADER.ordinal -> HeaderVH(view)
            ViewType.MOVIE.ordinal -> MovieVH(view)
            else -> throw IllegalStateException("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: AbstractViewHolder, position: Int) {
        val item = items[position]
        updateItem(holder, item)
    }

    override fun getItemId(position: Int): Long = items[position].id.toLong()

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType.ordinal
    }

    private fun updateItem(holder: AbstractViewHolder, item: AbstractItem) {
        when (holder) {
            is HeaderVH -> updateHeader(holder, item as Header)
            is MovieVH -> updateMovie(holder, item as Movie)
        }
    }

    private fun updateHeader(holder: HeaderVH, item: Header) {
        with(holder) {
            title.text = item.title
        }
    }

    private fun updateMovie(holder: MovieVH, item: Movie) {
        with(holder) {
            Glide.with(background)
                .load(item.pictureUrl)
                .into(background)
            age.text = item.age
            setFavouriteImage(holder.favourite, item.isFavourite)
            genre.text = item.genre
            rating.rating = item.rating
            reviews.text = item.reviews
            title.text = item.title
            duration.text = item.duration

            view.setOnClickListener { listener?.onItemClick(item.id) }
            favourite.setOnClickListener { listener?.onFavouriteClick(item.id) }
        }
    }

    private fun setFavouriteImage(favourite: ImageView, state: Boolean) {
        val favColor = if (state) R.color.wild_watermelon else R.color.white
        favourite.setImageResource(R.drawable.ic_favourite)
        favourite.setSvgColor(favColor)
    }

    interface ItemClickListener {
        fun onItemClick(id: Int)
        fun onFavouriteClick(id: Int)
    }

    abstract class AbstractViewHolder(open val view: View) : RecyclerView.ViewHolder(view)

    class HeaderVH(override val view: View) : AbstractViewHolder(view) {
        val title: TextView = view.findViewById(R.id.header)
    }

    class MovieVH(override val view: View) : AbstractViewHolder(view) {
        val background: ImageView = view.findViewById(R.id.movie_image)
        val age: TextView = view.findViewById(R.id.movie_age)
        val favourite: ImageView = view.findViewById(R.id.favourite)
        val genre: TextView = view.findViewById(R.id.movie_genre)
        val rating: RatingBar = view.findViewById(R.id.movie_rating)
        val reviews: TextView = view.findViewById(R.id.movie_reviews)
        val title: TextView = view.findViewById(R.id.movie_title)
        val duration: TextView = view.findViewById(R.id.movie_duration)
    }

    abstract class AbstractItem(
        open val id: Int,
        val viewType: ViewType
    )

    data class Header(
        override val id: Int,
        val title: String
    ) : AbstractItem(id, ViewType.HEADER)

    data class Movie(
        override val id: Int,
        val title: String,
        val genre: String,
        val rating: Float,
        val reviews: String,
        val age: String,
        val duration: String,
        val pictureUrl: String,
        val isFavourite: Boolean
    ) : AbstractItem(id, ViewType.MOVIE)

    enum class ViewType {
        HEADER, MOVIE
    }

    private class DiffUtilCallback : DiffUtil.Callback() {

        private var oldItems: List<AbstractItem> = emptyList()
        private var newItems: List<AbstractItem> = emptyList()

        fun setItems(oldItems: List<AbstractItem>, newItems: List<AbstractItem>) {
            this.oldItems = oldItems
            this.newItems = newItems
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].id == newItems[newItemPosition].id

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] == newItems[newItemPosition]
        }
    }

}