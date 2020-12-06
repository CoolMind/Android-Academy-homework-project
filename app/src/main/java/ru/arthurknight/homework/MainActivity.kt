package ru.arthurknight.homework

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun showMovieDetailsActivity() {
        val intent = MovieDetailsActivity.newIntent(this)
        startActivity(intent)
    }
}