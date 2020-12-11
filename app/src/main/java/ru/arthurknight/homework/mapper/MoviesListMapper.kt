package ru.arthurknight.homework.mapper

import android.content.res.Resources
import ru.arthurknight.homework.R
import ru.arthurknight.homework.adapter.MoviesListAdapter
import ru.arthurknight.homework.model.Movie

class MoviesListMapper(private val resources: Resources) : Mapper<Movie, MoviesListAdapter.Movie> {

    override fun map(item: Movie): MoviesListAdapter.Movie {
        return with(item) {
            val reviewsString = resources.getQuantityString(R.plurals.reviews_plurals, reviews, reviews)
            val durationString = "$duration min"
            MoviesListAdapter.Movie(
                id,
                name,
                genre,
                rating.toFloat(),
                reviewsString,
                age,
                durationString,
                thumbnailUrl,
                isFavourite
            )
        }
    }
}