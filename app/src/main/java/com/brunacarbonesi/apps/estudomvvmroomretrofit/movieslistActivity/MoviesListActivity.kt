package com.brunacarbonesi.apps.estudomvvmroomretrofit.movieslistActivity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.brunacarbonesi.apps.estudomvvmroomretrofit.R
import com.brunacarbonesi.apps.estudomvvmroomretrofit.movieDetailActivity.MovieDetailFragment
import com.brunacarbonesi.apps.estudomvvmroomretrofit.movieDetailActivity.MovieDetailFragmentDirections
import com.brunacarbonesi.apps.estudomvvmroomretrofit.utils.AppConstants
import com.google.android.material.transition.MaterialFadeThrough

class MoviesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun backToMoviesList(view: android.view.View) {
        val action = MovieDetailFragmentDirections.actionNavigationMovieDetailToMoviesList()
        view.findNavController().navigate(action)
    }

    fun aboutMe(view: android.view.View) {
        val action = MoviesListFragmentDirections.actionNavigationMoviesListToAboutApp()
        view.findNavController().navigate(action)
    }
}