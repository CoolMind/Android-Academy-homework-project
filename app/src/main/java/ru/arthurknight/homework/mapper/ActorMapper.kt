package ru.arthurknight.homework.mapper

import ru.arthurknight.homework.adapter.MovieActorsAdapter
import ru.arthurknight.homework.data.Actor

class ActorMapper : Mapper<Actor, MovieActorsAdapter.Actor> {

    override fun map(item: Actor): MovieActorsAdapter.Actor {
        return with(item) {
            MovieActorsAdapter.Actor(id, picture, name)
        }
    }
}