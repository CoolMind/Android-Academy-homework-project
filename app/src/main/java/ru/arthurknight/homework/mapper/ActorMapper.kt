package ru.arthurknight.homework.mapper

import ru.arthurknight.homework.adapter.MovieActorsAdapter
import ru.arthurknight.homework.model.Actor

class ActorMapper : Mapper<Actor, MovieActorsAdapter.Actor> {

    override fun map(item: Actor): MovieActorsAdapter.Actor {
        return with(item) {
            MovieActorsAdapter.Actor(id, photoUrl, name)
        }
    }
}