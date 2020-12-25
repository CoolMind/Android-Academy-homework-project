package ru.arthurknight.homework.mapper

interface Mapper<in A, out B> {
    fun map(item: A): B
}