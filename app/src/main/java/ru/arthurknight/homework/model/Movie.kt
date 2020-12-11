package ru.arthurknight.homework.model

data class Movie(
    val id: Int,
    val name: String,
    val rating: Double,
    val reviews: Int,
    val duration: Int,
    val age: String,
    val genre: String,
    val description: String,
    val detailsUrl: String,
    val thumbnailUrl: String,
    val actors: List<Actor>,
    val isFavourite: Boolean
)