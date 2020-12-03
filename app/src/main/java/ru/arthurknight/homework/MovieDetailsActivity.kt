package ru.arthurknight.homework

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import ru.arthurknight.homework.common.divider.DividerItemDecoration
import ru.arthurknight.homework.util.DrawableUtil


class MovieDetailsActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        findViewById<TextView>(R.id.movie_age).text = "13+"
        findViewById<TextView>(R.id.movie_title).text = "Avengers:\nEnd Game"
        findViewById<TextView>(R.id.movie_genre).text = "Action, Adventure, Fantasy"
        findViewById<TextView>(R.id.movie_reviews).text = "125 Reviews"
        findViewById<TextView>(R.id.movie_description).text =
            "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."
        createActorsList()

        findViewById<TextView>(R.id.back_button).setOnClickListener { finish() }
    }

    private fun createActorsList() {
        val actorsList = findViewById<RecyclerView>(R.id.movie_actors_list)
        val divider = DividerItemDecoration(LinearLayout.HORIZONTAL, false)
        divider.setDrawable(DrawableUtil.getDrawable(this, R.drawable.movie_list_divider))
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
        fun newIntent(context: Context): Intent =
            Intent(context, MovieDetailsActivity::class.java)
    }
}