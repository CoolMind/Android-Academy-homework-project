package ru.arthurknight.homework

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.arthurknight.homework.common.divider.DividerItemDecoration

class MovieDetailsActivity : AppCompatActivity(), View.OnClickListener {
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

        findViewById<ImageView>(R.id.back_icon).setOnClickListener(this)
        findViewById<TextView>(R.id.back_button).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.back_icon, R.id.back_button -> finish()
        }
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