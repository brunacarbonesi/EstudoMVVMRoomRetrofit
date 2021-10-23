package com.brunacarbonesi.apps.estudomvvmroomretrofit.movieslistActivity

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brunacarbonesi.apps.estudomvvmroomretrofit.R
import com.brunacarbonesi.apps.estudomvvmroomretrofit.aboutAppActivity.AboutAppActivity
import com.brunacarbonesi.apps.estudomvvmroomretrofit.adapter.MovieListAdapter
import com.brunacarbonesi.apps.estudomvvmroomretrofit.databinding.ActivityMainBinding
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO
import com.brunacarbonesi.apps.estudomvvmroomretrofit.utils.NetworkHelper
import com.brunacarbonesi.apps.estudomvvmroomretrofit.utils.ViewStatus
import com.google.android.material.snackbar.Snackbar

class MoviesListActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var moviesListViewModel: MoviesListViewModel? = null

    private val factory = MoviesListViewModel.Factory()

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var moviesListAdapter: MovieListAdapter
    private var isNetworkAvailable: Boolean = false

    private lateinit var moviesList: List<MovieVO>

    private var savedRecyclerLayoutState: Parcelable? = null

    private val numberOfColumns = 3

    companion object {
        private const val SAVE_STATE = "save state"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(R.anim.slide_in_right, R.anim.stop)

        moviesListViewModel = ViewModelProvider(this, factory).get(MoviesListViewModel::class.java)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        setupView()
        applyObserver()

        if (savedInstanceState != null) {
            val savedRecyclerLayoutState: Parcelable? = savedInstanceState.getParcelable(SAVE_STATE)
            recyclerView.layoutManager?.onRestoreInstanceState(savedRecyclerLayoutState)
        } else {
            isNetworkAvailable = NetworkHelper.isNetworkConnected(applicationContext)
            moviesListViewModel?.fillMoviesList(isNetworkAvailable)
        }

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putParcelable(SAVE_STATE, recyclerView.layoutManager?.onSaveInstanceState())
    }

    private fun applyObserver() {
        moviesListViewModel?.liveDataMovie?.observe(this, {viewData ->
            when(viewData.viewStatus) {
                ViewStatus.SUCCESS -> {
                    if(viewData.data != null) {
                        moviesList = viewData.data
                        setRecyclerView(moviesList)
                    }
                }
                ViewStatus.ERROR -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE

                    val message = getString(R.string.error)
                    Snackbar.make(binding.mainActivityView, message, Snackbar.LENGTH_LONG).show()
                }
                ViewStatus.NO_VALUE -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    binding.mainActivityListaVazia.visibility = View.VISIBLE
                }
                ViewStatus.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupView() {
        progressBar = binding.movieListProgressBar
        recyclerView = binding.recyclerView

        setContentView(binding.root)
        moviesListAdapter = MovieListAdapter()
    }

    private fun setRecyclerView(moviesList: List<MovieVO>) {

        moviesListAdapter.movies = moviesList

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, numberOfColumns)
            adapter = moviesListAdapter
        }

        recyclerViewLayoutState(recyclerView)

        progressBar.visibility = View.GONE

    }

    private fun recyclerViewLayoutState(recyclerView: RecyclerView) {
        if (savedRecyclerLayoutState != null) {
            recyclerView.layoutManager?.onRestoreInstanceState(savedRecyclerLayoutState)
        }
    }

    fun aboutMe(view: android.view.View) {
        val intent = Intent(this, AboutAppActivity::class.java)
        startActivity(intent)
    }
}