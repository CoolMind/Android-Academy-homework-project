package ru.arthurknight.homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.arthurknight.homework.adapter.MovieActorsAdapter
import ru.arthurknight.homework.common.divider.DividerItemDecoration
import ru.arthurknight.homework.data.Movie
import ru.arthurknight.homework.mapper.ActorMapper
import ru.arthurknight.homework.util.DrawableUtil

class FragmentMoviesDetails : Fragment() {

    private lateinit var actorMapper: ActorMapper
    private lateinit var movie: Movie
    private lateinit var actors: List<MovieActorsAdapter.Actor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movie = requireArguments().getSerializable(ARG_MOVIE) as Movie

        actorMapper = ActorMapper()
        actors = movie.actors.map { actorMapper.map(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)

        view.findViewById<ImageView>(R.id.movie_image).let { background ->
            Glide.with(background)
                .load(movie.backdrop)
                .into(background)
        }
        view.findViewById<TextView>(R.id.movie_age).text =
            getString(R.string.movie_age, movie.minimumAge)
        view.findViewById<TextView>(R.id.movie_title).text = movie.title
        view.findViewById<TextView>(R.id.movie_genre).text =
            movie.genres.joinToString(", ") { it.name }
        view.findViewById<TextView>(R.id.movie_reviews).text = resources.getQuantityString(
            R.plurals.reviews_plurals,
            movie.numberOfRatings,
            movie.numberOfRatings
        )
        view.findViewById<TextView>(R.id.movie_description).text = movie.overview
        createActorsList(view)

        view.findViewById<TextView>(R.id.back_button)
            .setOnClickListener { (activity as AppCompatActivity).onBackPressed() }

        return view
    }

    private fun createActorsList(view: View) {
        val cast = view.findViewById<TextView>(R.id.movie_cast)
        if (actors.isEmpty()) {
            cast.visibility = View.GONE
        }
        val actorsList = view.findViewById<RecyclerView>(R.id.movie_actors_list)
        val divider = DividerItemDecoration(LinearLayout.HORIZONTAL, false)
        context?.let {
            divider.setDrawable(DrawableUtil.getDrawable(it, R.drawable.movie_actors_divider))
        }
        val adapter = MovieActorsAdapter().apply {
            setHasStableIds(true)
            setItems(actors)
        }
        with(actorsList) {
            this.adapter = adapter
            addItemDecoration(divider)
            setHasFixedSize(true)
        }
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(actorsList)
    }

    companion object {

        val TAG: String = FragmentMoviesDetails::class.java.name

        private const val ARG_MOVIE = "ARG_MOVIE"

        fun newInstance(movie: Movie) =
            FragmentMoviesDetails().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_MOVIE, movie)
                }
            }
    }
}