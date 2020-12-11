package ru.arthurknight.homework.repository

import ru.arthurknight.homework.model.Actor
import ru.arthurknight.homework.model.Movie
import ru.arthurknight.homework.model.MovieActors

class Repository {

    /**
     * Список фильмов.
     */
    fun getMovies(): List<Movie> {
        val movieActors = getMovieActors()
        return listOf(
            Movie(
                1,
                "Dolittle",
                2.3,
                87,
                101,
                "PG",
                "Adventure, Comedy, Family",
                "A physician who can talk to animals embarks on an adventure to find a legendary island with a young apprentice and a crew of strange pets.",
                "http://lardis.ru/academ/webp/pic_movie_image_in_details_dolittle.webp",
                "http://lardis.ru/academ/webp/pic_movie_image_in_list_dolittle.webp",
                getActors(movieActors, 1),
                false
            ),
            Movie(
                2,
                "Underwater",
                2.6,
                113,
                95,
                "18+",
                "Action, Horror, Sci-Fi",
                "A crew of oceanic researchers working for a deep sea drilling company try to get to safety after a mysterious earthquake devastates their deepwater research and drilling facility located at the bottom of the Mariana Trench.",
                "http://lardis.ru/academ/webp/pic_movie_image_in_details_underwater.webp",
                "http://lardis.ru/academ/webp/pic_movie_image_in_list_underwater.webp",
                getActors(movieActors, 2),
                true
            ),
            Movie(
                3,
                "The Call Of The Wild",
                3.4,
                321,
                119,
                "PG",
                "Adventure, Drama, Family",
                "A sled dog struggles for survival in the wilds of the Yukon.",
                "http://lardis.ru/academ/webp/pic_movie_image_in_details_the_call_of_the_wild.webp",
                "http://lardis.ru/academ/webp/pic_movie_image_in_list_the_call_of_the_wild.webp",
                getActors(movieActors, 3),
                false
            ),
            Movie(
                4,
                "Last Christmas",
                3.3,
                208,
                121,
                "13+",
                "Comedy, Drama, Romance",
                "Kate is a young woman subscribed to bad decisions. Working as an elf in a year round Christmas store is not good for the wannabe singer. However, she meets Tom there. Her life takes a new turn. For Kate, it seems too good to be true.",
                "http://lardis.ru/academ/webp/pic_movie_image_in_details_last_christmas.webp",
                "http://lardis.ru/academ/webp/pic_movie_image_in_list_last_christmas.webp",
                getActors(movieActors, 4),
                false
            ),
            Movie(
                5,
                "The Invisible Guest",
                4.0,
                173,
                106,
                "16+",
                "Crime, Drama, Mystery",
                "A successful entrepreneur accused of murder and a witness preparation expert have less than three hours to come up with an impregnable defense.",
                "http://lardis.ru/academ/webp/pic_movie_image_in_details_the_invisible_guest.webp",
                "http://lardis.ru/academ/webp/pic_movie_image_in_list_the_invisible_guest.webp",
                getActors(movieActors, 5),
                false
            ),
            Movie(
                6,
                "Fantasy Island",
                2.4,
                181,
                109,
                "13+",
                "Action, Adventure, Fantasy",
                "When the owner and operator of a luxurious island invites a collection of guests to live out their most elaborate fantasies in relative seclusion, chaos quickly descends.",
                "http://lardis.ru/academ/webp/pic_movie_image_in_details_fantasy_island.webp",
                "http://lardis.ru/academ/webp/pic_movie_image_in_list_fantasy_island.webp",
                getActors(movieActors, 6),
                false
            )
        )
    }

    /**
     * Список актёров.
     */
    fun getActors(): List<Actor> = listOf(
        Actor(
            1,
            "Robert Downey Jr.",
            "http://lardis.ru/academ/webp/pic_actor_photo_robert_downey_jr.webp"
        ),
        Actor(
            2,
            "Antonio Banderas",
            "http://lardis.ru/academ/webp/pic_actor_photo_antonio_banderas.webp"
        ),
        Actor(
            3,
            "Michael Sheen",
            "http://lardis.ru/academ/webp/pic_actor_photo_michael_sheen.webp"
        ),

        Actor(
            4,
            "Jim Broadbent",
            "http://lardis.ru/academ/webp/pic_actor_photo_jim_broadbent.webp"
        ),
        Actor(
            5,
            "Jessie Buckley",
            "http://lardis.ru/academ/webp/pic_actor_photo_jessie_buckley.webp"
        ),
        Actor(
            6,
            "Harry Colett",
            "http://lardis.ru/academ/webp/pic_actor_photo_harry_colett.webp"
        ),
        Actor(
            7,
            "Kristen Stewart",
            "http://lardis.ru/academ/webp/pic_actor_photo_kristen_stewart.webp"
        ),
        Actor(
            8,
            "Vincent Cassel",
            "http://lardis.ru/academ/webp/pic_actor_photo_vincent_cassel.webp"
        ),
        Actor(
            9,
            "Mamoudou Athie",
            "http://lardis.ru/academ/webp/pic_actor_photo_mamoudou_athie.webp"
        ),
        Actor(
            10,
            "T. J. Miller",
            "http://lardis.ru/academ/webp/pic_actor_photo_tj_miller.webp"
        ),
        Actor(
            11,
            "John Gallagher Jr.",
            "http://lardis.ru/academ/webp/pic_actor_photo_john_gallagher_jr.webp"
        ),
        Actor(
            12,
            "Jessica Henwick",
            "http://lardis.ru/academ/webp/pic_actor_photo_jessica_henwick.webp"
        ),
        Actor(
            13,
            "Gunner Wright",
            "http://lardis.ru/academ/webp/pic_actor_photo_gunner_wright.webp"
        ),
        Actor(
            14,
            "Fiona Rene",
            "http://lardis.ru/academ/webp/pic_actor_photo_fiona_rene.webp"
        ),
        Actor(
            15,
            "Amanda Troop",
            "http://lardis.ru/academ/webp/pic_actor_photo_amanda_troop.webp"
        ),
        Actor(
            16,
            "Harrison Ford",
            "http://lardis.ru/academ/webp/pic_actor_photo_harrison_ford.webp"
        ),
        Actor(
            17,
            "Omar Sy",
            "http://lardis.ru/academ/webp/pic_actor_photo_omar_sy.webp"
        ),
        Actor(
            18,
            "Cara Gee",
            "http://lardis.ru/academ/webp/pic_actor_photo_cara_gee.webp"
        ),
        Actor(
            19,
            "Dan Stevens",
            "http://lardis.ru/academ/webp/pic_actor_photo_dan_stevens.webp"
        ),
        Actor(
            20,
            "Bradley Whitford",
            "http://lardis.ru/academ/webp/pic_actor_photo_bradley_whitford.webp"
        ),
        Actor(
            21,
            "Jean Louisa Kelly",
            "http://lardis.ru/academ/webp/pic_actor_photo_jean_louisa_kelly.webp"
        ),
        Actor(
            22,
            "Emilia Clarke",
            "http://lardis.ru/academ/webp/pic_actor_photo_emilia_clarke.webp"
        ),
        Actor(
            23,
            "Madison Ingoldsby",
            "http://lardis.ru/academ/webp/pic_actor_photo_madison_ingoldsby.webp"
        ),
        Actor(
            24,
            "Emma Thompson",
            "http://lardis.ru/academ/webp/pic_actor_photo_emma_thompson.webp"
        ),
        Actor(
            25,
            "Boris Isakovic",
            "http://lardis.ru/academ/webp/pic_actor_photo_boris_isakovich.webp"
        ),
        Actor(
            26,
            "Maxim Baldry",
            "http://lardis.ru/academ/webp/pic_actor_photo_maxim_baldry.webp"
        ),
        Actor(
            27,
            "Mario Casas",
            "http://lardis.ru/academ/webp/pic_actor_photo_mario_casas.webp"
        ),
        Actor(
            28,
            "Ana Wagener",
            "http://lardis.ru/academ/webp/pic_actor_photo_ana_wagener.webp"
        ),
        Actor(
            29,
            "Barbara Lennie",
            "http://lardis.ru/academ/webp/pic_actor_photo_barbara_lennie.webp"
        ),
        Actor(
            30,
            "Francesc Orella",
            "http://lardis.ru/academ/webp/pic_actor_photo_francesc_orella.webp"
        ),
        Actor(
            31,
            "Paco Tous",
            "http://lardis.ru/academ/webp/pic_actor_photo_paco_tous.webp"
        ),
        Actor(
            32,
            "Michael Pena",
            "http://lardis.ru/academ/webp/pic_actor_photo_michael_pena.webp"
        ),
        Actor(
            33,
            "Maggie Q",
            "http://lardis.ru/academ/webp/pic_actor_photo_maggie_q.webp"
        ),
        Actor(
            34,
            "Lucy Hale",
            "http://lardis.ru/academ/webp/pic_actor_photo_lucy_hale.webp"
        ),
        Actor(
            35,
            "Austin Stowell",
            "http://lardis.ru/academ/webp/pic_actor_photo_austin_stowell.webp"
        ),
        Actor(
            36,
            "Jimmy O. Yang",
            "http://lardis.ru/academ/webp/pic_actor_photo_jummy_o_yang.webp"
        ),
        Actor(
            37,
            "Portia Doubleday",
            "http://lardis.ru/academ/webp/pic_actor_photo_portia_doubleday.webp"
        )
    )

    private fun getMovieActors(): List<MovieActors> = listOf(
        MovieActors(1, (1..6).toList()),
        MovieActors(2, (7..15).toList()),
        MovieActors(3, (16..21).toList()),
        MovieActors(4, (22..26).toList()),
        MovieActors(5, (27..31).toList()),
        MovieActors(6, (32..37).toList())
    )

    private fun getActors(movieActors: List<MovieActors>, movieId: Int): List<Actor> {
        val actorIds = movieActors.first { it.movieId == movieId }.actorIds
        return getActors().filter { it.id in actorIds }
    }
}