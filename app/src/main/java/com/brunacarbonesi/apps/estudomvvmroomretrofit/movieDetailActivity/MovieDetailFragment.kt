package com.brunacarbonesi.apps.estudomvvmroomretrofit.movieDetailActivity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.brunacarbonesi.apps.estudomvvmroomretrofit.R
import com.brunacarbonesi.apps.estudomvvmroomretrofit.databinding.FragmentMovieDetailBinding
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO
import com.google.android.material.transition.MaterialContainerTransform
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private var movieDetailViewModel: MovieDetailViewModel? = null
    private val factory = MovieDetailViewModel.Factory()

    private val args: MovieDetailFragmentArgs by navArgs()
    private lateinit var movieVO: MovieVO

    companion object {

        @JvmStatic
        fun newInstance() =
            MovieDetailFragment.apply {
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        movieDetailViewModel =
            ViewModelProvider(this, factory).get(MovieDetailViewModel::class.java)

        activity?.apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        sharedElementEnterTransition = MaterialContainerTransform()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        applyObserver()
    }

    private fun applyObserver() {
        movieDetailViewModel?.liveDataMovieDetail?.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { updateViews() })
    }

    private fun updateViews() {
        setupView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieVO = args.movieVO
        binding.movieDetailPoster.transitionName = movieVO.id.toString()
        binding.movie = movieVO

        setupView()
    }

    private fun setupView() {

        binding.movieDetailRating.text = movieVO.rating.toString()
        binding.rating.rating = movieVO.rating / 2

        val releaseDateFormatted = formatReleaseDate(movieVO.releaseDate)
        binding.movieDetailReleaseDate.text = releaseDateFormatted

    }

    fun formatReleaseDate(date: String): String {
        val releaseDate: String
        val outputText: String
        val outputFormat: DateFormat = SimpleDateFormat("MM/yyyy", Locale.US)
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        val dateReceived: Date? = inputFormat.parse(date)
        if (dateReceived != null) {
            outputText = outputFormat.format(dateReceived)
            releaseDate = getString(R.string.release_date, outputText)
        } else {
            releaseDate = "Sem informação"
            binding.movieDetailReleaseDate.visibility = View.GONE
        }

        return releaseDate
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        }
    }
}