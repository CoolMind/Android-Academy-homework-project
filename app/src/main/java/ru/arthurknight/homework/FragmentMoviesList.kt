package ru.arthurknight.homework

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.arthurknight.homework.adapter.MoviesListAdapter
import ru.arthurknight.homework.common.divider.GridDividerItemDecoration
import ru.arthurknight.homework.mapper.MoviesListMapper
import ru.arthurknight.homework.model.Movie
import ru.arthurknight.homework.repository.Repository
import ru.arthurknight.homework.util.DrawableUtil

/**
 * A fragment representing a list of movies.
 */
class FragmentMoviesList : Fragment(), MoviesListAdapter.ItemClickListener {

    private var items = mutableListOf<MoviesListAdapter.AbstractItem>()
    private var listener: MovieClickListener? = null
    private lateinit var repository: Repository
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

        repository = Repository()
        moviesListMapper = MoviesListMapper(resources)
        items = getItems(repository.getMovies()).toMutableList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.list)

        createMoviesList(recyclerView)
        return view
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    override fun onItemClick(id: Int) {
        (items.firstOrNull { it.id == id } as? MoviesListAdapter.Movie)?.let {
            listener?.onMovieCardClick(id)
        }
    }

    override fun onFavouriteClick(id: Int) {
        val item = adapter.getItem(id) as MoviesListAdapter.Movie
        val position = items.indexOf(item)
        val copy = item.copy(isFavourite = !item.isFavourite)
        items[position] = copy
        adapter.setItem(copy)
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
        fun onMovieCardClick(id: Int)
    }

    companion object {

        val TAG: String = FragmentMoviesList::class.java.name

        fun newInstance() = FragmentMoviesList().apply {
            arguments = Bundle().apply {
            }
        }
    }
}