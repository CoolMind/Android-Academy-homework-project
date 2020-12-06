package ru.arthurknight.homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import ru.arthurknight.homework.common.divider.DividerItemDecoration
import ru.arthurknight.homework.util.DrawableUtil

class FragmentMoviesDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)


        view.findViewById<TextView>(R.id.movie_age).text = "13+"
        view.findViewById<TextView>(R.id.movie_title).text = "Avengers:\nEnd Game"
        view.findViewById<TextView>(R.id.movie_genre).text = "Action, Adventure, Fantasy"
        view.findViewById<TextView>(R.id.movie_reviews).text = "125 Reviews"
        view.findViewById<TextView>(R.id.movie_description).text =
            "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."
        createActorsList(view)

        view.findViewById<TextView>(R.id.back_button).setOnClickListener { activity?.finish() }

        return view
    }

    private fun createActorsList(view: View) {
        val actorsList = view.findViewById<RecyclerView>(R.id.movie_actors_list)
        val divider = DividerItemDecoration(LinearLayout.HORIZONTAL, false)
        context?.let {
            divider.setDrawable(DrawableUtil.getDrawable(it, R.drawable.movie_list_divider))
        }
        val adapter = MovieDetailsAdapter()
        val items = listOf(
            MovieDetailsAdapter.Item(0, R.drawable.actor1, "Robert Downey Jr."),
            MovieDetailsAdapter.Item(1, R.drawable.actor2, "Chris Evans"),
            MovieDetailsAdapter.Item(2, R.drawable.actor3, "Mark Ruffalo"),
            MovieDetailsAdapter.Item(3, R.drawable.actor4, "Chris Hemsworth"),
        )
        adapter.setItems(items)
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

        fun newInstance() =
            FragmentMoviesDetails().apply {
                arguments = Bundle().apply {
                }
            }
    }
}