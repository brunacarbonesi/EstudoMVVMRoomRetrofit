package com.brunacarbonesi.apps.estudomvvmroomretrofit.movieslistActivity

import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brunacarbonesi.apps.estudomvvmroomretrofit.R
import com.brunacarbonesi.apps.estudomvvmroomretrofit.adapter.MovieListAdapter
import com.brunacarbonesi.apps.estudomvvmroomretrofit.adapter.OnClickListener
import com.brunacarbonesi.apps.estudomvvmroomretrofit.databinding.FragmentMoviesListBinding
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO
import com.brunacarbonesi.apps.estudomvvmroomretrofit.utils.NetworkHelper
import com.brunacarbonesi.apps.estudomvvmroomretrofit.utils.ViewStatus
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialElevationScale

class MoviesListFragment : Fragment() {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private var moviesListViewModel: MoviesListViewModel? = null
    private val factory = MoviesListViewModel.Factory()

    private lateinit var recyclerView: RecyclerView
    private var numberOfColumns: Int = 3

    private lateinit var progressBar: ProgressBar
    private lateinit var moviesListAdapter: MovieListAdapter
    private var isNetworkAvailable: Boolean = false

    private lateinit var moviesList: List<MovieVO>

    private var savedRecyclerLayoutState: Parcelable? = null


    companion object {
        const val SAVE_STATE = "save state"

        @JvmStatic
        fun newInstance() =
            MoviesListFragment().apply {
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exitTransition = MaterialElevationScale(false)
        reenterTransition = MaterialElevationScale(true)

        Log.d("OnCreate", "ok")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("OnCreateView", "ok")
        moviesListViewModel =
            ViewModelProvider(this, factory).get(MoviesListViewModel::class.java)

        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)

        setupView()
        applyObserver()

        if (savedInstanceState != null) {
            val savedRecyclerLayoutState: Parcelable? = savedInstanceState.getParcelable(SAVE_STATE)
            recyclerView.layoutManager?.onRestoreInstanceState(savedRecyclerLayoutState)
        } else {
            isNetworkAvailable = NetworkHelper.isNetworkConnected(requireContext())
            moviesListViewModel?.fillMoviesList(isNetworkAvailable)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        Log.d("OnSaveInstanceState", "ok")
//        outState.putParcelable(SAVE_STATE, recyclerView.layoutManager?.onSaveInstanceState())
//    }

    private fun applyObserver() {
        Log.d("Apply observer", "ok")
        moviesListViewModel?.liveDataMovie?.observe(viewLifecycleOwner, { viewData ->
            when (viewData.viewStatus) {
                ViewStatus.SUCCESS -> {
                    if (viewData.data != null) {
                        moviesList = viewData.data
                        numberOfColumns = numberOfColumnsDefinition()
                        setRecyclerView(moviesList)
                        progressBar.visibility = View.GONE
                    }
                }
                ViewStatus.ERROR -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE

                    val message = getString(R.string.error)
                    Snackbar.make(binding.fragmentMoviesListView, message, Snackbar.LENGTH_LONG)
                        .show()
                }
                ViewStatus.NO_VALUE -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    binding.moviesListEmptyList.moviesListEmptyListLayout.visibility = View.VISIBLE
                }
                ViewStatus.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupView() {
        Log.d("SetupView", "ok")
        progressBar = binding.movieListProgressBar
        recyclerView = binding.recyclerView

        moviesListAdapter = MovieListAdapter()

    }

    private fun setRecyclerView(moviesList: List<MovieVO>) {
        Log.d("SetRecyclerView", "ok")
        moviesListAdapter.movies = moviesList

        moviesListAdapter.listener = object : OnClickListener {
            override fun onClick(movieVO: MovieVO, imageView: ImageView) {
                val extras = FragmentNavigatorExtras(
                    imageView to movieVO.id.toString()
                )
                val action =
                    MoviesListFragmentDirections.actionNavigationMoviesListToMovieDetail(movieVO = movieVO)
                findNavController().navigate(action, extras)
            }
        }

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, numberOfColumns)
            adapter = moviesListAdapter
        }

        recyclerViewLayoutState(recyclerView)
    }

    private fun recyclerViewLayoutState(recyclerView: RecyclerView) {
        Log.d("RecyclerViewLayoutState", "ok")
        if (savedRecyclerLayoutState != null) {
            recyclerView.layoutManager?.onRestoreInstanceState(savedRecyclerLayoutState)
        }
    }

    fun numberOfColumnsDefinition(): Int {
        Log.d("NumberOfColumns", "ok")
        val variableNumberOfColumns: Int =
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                6
            } else {
                3
            }
        return variableNumberOfColumns
    }

}