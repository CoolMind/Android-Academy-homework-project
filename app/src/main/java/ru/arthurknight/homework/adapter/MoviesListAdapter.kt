package ru.arthurknight.homework.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.arthurknight.homework.R
import ru.arthurknight.homework.util.DrawableUtil.setSvgColor

class MoviesListAdapter(private val values: List<AbstractItem>) :
    RecyclerView.Adapter<MoviesListAdapter.AbstractViewHolder>() {

    private var listener: ItemClickListener? = null


    fun setClickListener(listener: ItemClickListener?) {
        this.listener = listener
    }

    fun isHeader(position: Int): Boolean = getItemViewType(position) == ViewType.HEADER.ordinal

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
        val item = values[position]
        updateItem(holder, item)
    }

    override fun getItemId(position: Int): Long = values[position].id.toLong()

    override fun getItemCount(): Int = values.size

    override fun getItemViewType(position: Int): Int = values[position].viewType.ordinal

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
            background.setImageResource(item.background)
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
        val background: Int,
        val age: String,
        val isFavourite: Boolean,
        val genre: String,
        val rating: Float,
        val reviews: String,
        val title: String,
        val duration: String
    ) : AbstractItem(id, ViewType.MOVIE)

    enum class ViewType {
        HEADER, MOVIE
    }
}