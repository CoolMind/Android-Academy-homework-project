package ru.arthurknight.homework.data

import java.io.Serializable

@kotlinx.serialization.Serializable
data class Genre(val id: Int, val name: String) : Serializable