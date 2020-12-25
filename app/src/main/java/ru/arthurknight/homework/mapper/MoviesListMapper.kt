package ru.arthurknight.homework.mapper

import android.content.res.Resources
import ru.arthurknight.homework.R
import ru.arthurknight.homework.adapter.MoviesListAdapter
import ru.arthurknight.homework.data.Movie

class MoviesListMapper(private val resources: Resources) : Mapper<Movie, MoviesListAdapter.Movie> {

    override fun map(item: Movie): MoviesListAdapter.Movie {
        with(item) {
            val genre = genres.joinToString(", ") { it.name }
            val rating = ratings / 2f
            val reviews = resources.getQuantityString(
                R.plurals.reviews_plurals,
                numberOfRatings,
                numberOfRatings
            )
            val age = resources.getString(R.string.movie_age, minimumAge)
            val duration = resources.getString(R.string.movie_duration, runtime)

            return MoviesListAdapter.Movie(
                id,
                title,
                genre,
                rating,
                reviews,
                age,
                duration,
                poster,
                false
            )
        }
    }
}