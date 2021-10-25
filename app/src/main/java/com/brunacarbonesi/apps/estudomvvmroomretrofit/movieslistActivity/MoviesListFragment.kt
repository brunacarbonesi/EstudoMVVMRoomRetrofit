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
import com.brunacarbonesi.apps.estudomvvmroomretrofit.utils.Definitions
import com.brunacarbonesi.apps.estudomvvmroomretrofit.utils.NetworkHelper
import com.brunacarbonesi.apps.estudomvvmroomretrofit.utils.ViewStatus
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialContainerTransform
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

    companion object {
        @JvmStatic
        fun newInstance() =
            MoviesListFragment().apply {
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exitTransition = MaterialElevationScale(false)
        reenterTransition = MaterialElevationScale(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)

        moviesListViewModel =
            ViewModelProvider(this, factory).get(MoviesListViewModel::class.java)

        setupView()
        applyObserver()
        startMethodToObserve()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        numberOfColumns = Definitions.numberOfColumnsDefinition(resources)

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, numberOfColumns)
            adapter = moviesListAdapter
        }
    }

    private fun setupView() {
        progressBar = binding.movieListProgressBar
        recyclerView = binding.recyclerView

        moviesListAdapter = MovieListAdapter()
    }

    private fun applyObserver() {
        moviesListViewModel?.liveDataMovie?.observe(viewLifecycleOwner, { viewData ->
            when (viewData.viewStatus) {
                ViewStatus.SUCCESS -> {
                    if (viewData.data != null) {
                        moviesList = viewData.data
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

    private fun startMethodToObserve() {
        isNetworkAvailable = NetworkHelper.isNetworkConnected(requireContext())
        moviesListViewModel?.fillMoviesList(isNetworkAvailable)
    }

    private fun setRecyclerView(moviesList: List<MovieVO>) {
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
    }
}