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

/**
 * A fragment representing a list of movies.
 */
class FragmentMoviesList : Fragment(), MoviesListAdapter.ItemClickListener {

    private var items: List<MoviesListAdapter.AbstractItem> = emptyList()
    private var listener: MovieClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MovieClickListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement ${MoviesListAdapter.ItemClickListener::class.java.name}")
        }
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
    }

    private fun getItems(): List<MoviesListAdapter.AbstractItem> = listOf(
        MoviesListAdapter.Header(10, "Movies list"),
        MoviesListAdapter.Movie(
            11,
            R.drawable.movie_background,
            "13+",
            false,
            "Action, Adventure, Drama",
            4f,
            "125 Reviews",
            "Avengers: End Game",
            "137 min"
        )
    )

    private fun createMoviesList(recyclerView: RecyclerView) {
        items = getItems()
        val adapter = MoviesListAdapter(items)
        adapter.setClickListener(this)

        with(recyclerView) {
            this.layoutManager = layoutManager
            this.adapter = adapter
            setHasFixedSize(true)
        }
        // Выделение целой строки для заголовка.
        val layoutManager = recyclerView.layoutManager as GridLayoutManager
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int =
                if (adapter.isHeader(position)) layoutManager.spanCount else 1
        }
    }


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