package ru.arthurknight.homework

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.arthurknight.homework.adapter.MoviesListAdapter
import ru.arthurknight.homework.common.divider.GridDividerItemDecoration
import ru.arthurknight.homework.data.Movie
import ru.arthurknight.homework.data.loadMovies
import ru.arthurknight.homework.mapper.MoviesListMapper
import ru.arthurknight.homework.util.DrawableUtil

/**
 * A fragment representing a list of movies.
 */
class FragmentMoviesList : Fragment(), MoviesListAdapter.ItemClickListener {

    private lateinit var movies: List<Movie>
    private lateinit var items: MutableList<MoviesListAdapter.AbstractItem>
    private var isDataLoaded = false
    private var listener: MovieClickListener? = null
    private lateinit var moviesListMapper: MoviesListMapper
    private lateinit var adapter: MoviesListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MovieClickListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement ${MoviesListAdapter.ItemClickListener::class.java.name}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moviesListMapper = MoviesListMapper(resources)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.list)

        if (isDataLoaded) {
            createMoviesList(recyclerView)
        } else {
            lifecycleScope.launchWhenStarted {
                movies = loadMovies(requireContext())
                items = getItems(movies).toMutableList()
                isDataLoaded = true
                createMoviesList(recyclerView)
            }
        }
        return view
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    override fun onItemClick(id: Int) {
        if (isDataLoaded) {
            movies.firstOrNull { it.id == id }?.let { listener?.onMovieCardClick(it) }
        }
    }

    override fun onFavouriteClick(id: Int) {
        if (isDataLoaded) {
            val position = items.indexOfFirst { it.id == id }
            val item = items[position] as MoviesListAdapter.Movie
            val copy = item.copy(isFavourite = !item.isFavourite)
            items[position] = copy
            adapter.setItems(items)
        }
    }

    private fun createMoviesList(recyclerView: RecyclerView) {
        adapter = MoviesListAdapter().apply {
            setItems(items)
            setHasStableIds(true)
            setClickListener(this@FragmentMoviesList)
        }

        val divider = GridDividerItemDecoration()
        context?.let {
            divider.setDrawable(DrawableUtil.getDrawable(it, R.drawable.movie_thumbnails_divider))
        }
        divider.skipHeaderDivider = true
        with(recyclerView) {
            this.layoutManager = layoutManager
            this.adapter = this@FragmentMoviesList.adapter
            addItemDecoration(divider)
            setHasFixedSize(true)
        }
        // Выделение целой строки для заголовка.
        (recyclerView.layoutManager as? GridLayoutManager)?.let { layoutManager ->
            val spanCount = layoutManager.spanCount
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int =
                    if (adapter.isHeader(position)) spanCount else 1
            }
        }
    }

    private fun getItems(movies: List<Movie>): List<MoviesListAdapter.AbstractItem> =
        listOf(MoviesListAdapter.Header(0, getString(R.string.movie_movies_list))) +
                movies.map { moviesListMapper.map(it) }

    interface MovieClickListener {
        fun onMovieCardClick(movie: Movie)
    }

    companion object {

        val TAG: String = FragmentMoviesList::class.java.name

        fun newInstance() = FragmentMoviesList().apply {
            arguments = Bundle().apply {
            }
        }
    }
}