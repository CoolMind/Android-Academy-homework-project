package ru.arthurknight.homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.arthurknight.homework.adapter.MovieAdapter
import ru.arthurknight.homework.dummy.DummyContent

/**
 * A fragment representing a list of Items.
 */
class FragmentMoviesList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = GridLayoutManager(context, 2)
                adapter = MovieAdapter(DummyContent.ITEMS)
            }
        }
        return view
    }


    companion object {

        val TAG: String = FragmentMoviesList::class.java.name

        fun newInstance() = FragmentMoviesList().apply {
            arguments = Bundle().apply {
            }
        }
    }
}