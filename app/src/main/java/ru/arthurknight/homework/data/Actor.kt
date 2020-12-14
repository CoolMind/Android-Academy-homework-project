package ru.arthurknight.homework.data

import java.io.Serializable

data class Actor(
    val id: Int,
    val name: String,
    val picture: String
) : Serializable