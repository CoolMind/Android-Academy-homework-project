package ru.arthurknight.homework

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.arthurknight.homework.common.divider.DividerItemDecoration

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        createActorsList()
    }

    private fun createActorsList() {
        val actorsList = findViewById<RecyclerView>(R.id.movie_actors_list)
        val divider = DividerItemDecoration(LinearLayout.HORIZONTAL, false)
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.movie_list_divider)!!)
        val adapter = MovieDetailsAdapter()
        val items = arrayListOf(
            MovieDetailsAdapter.Item(R.drawable.actor1, "Robert Downey Jr."),
            MovieDetailsAdapter.Item(R.drawable.actor2, "Chris Evans"),
            MovieDetailsAdapter.Item(R.drawable.actor3, "Mark Ruffalo"),
            MovieDetailsAdapter.Item(R.drawable.actor4, "Chris Hemsworth"),
        )
        adapter.setItems(items)
        with(actorsList) {
            layoutManager = LinearLayoutManager(
                this@MovieDetailsActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            this.adapter = adapter
            addItemDecoration(divider)
            setHasFixedSize(true)
        }
    }
}