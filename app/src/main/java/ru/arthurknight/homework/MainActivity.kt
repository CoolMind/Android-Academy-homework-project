package ru.arthurknight.homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.arthurknight.homework.data.Movie

class MainActivity : AppCompatActivity(), FragmentMoviesList.MovieClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    FragmentMoviesList.newInstance(),
                    FragmentMoviesList.TAG
                )
                .commit()
        }
    }

//    override fun onBackPressed() {
//        val count = supportFragmentManager.backStackEntryCount
//        if (count == 0) {
//            super.onBackPressed()
//        } else {
//            supportFragmentManager.popBackStack()
//        }
//    }

    override fun onMovieCardClick(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                FragmentMoviesDetails.newInstance(movie),
                FragmentMoviesDetails.TAG
            )
            .addToBackStack(FragmentMoviesDetails.TAG)
            .commit()
    }
}