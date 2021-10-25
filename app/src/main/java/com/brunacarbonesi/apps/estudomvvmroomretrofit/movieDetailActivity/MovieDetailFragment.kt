package com.brunacarbonesi.apps.estudomvvmroomretrofit.movieDetailActivity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.brunacarbonesi.apps.estudomvvmroomretrofit.databinding.FragmentMovieDetailBinding
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO
import com.brunacarbonesi.apps.estudomvvmroomretrofit.utils.Definitions
import com.google.android.material.transition.MaterialContainerTransform

class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

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

        activity?.apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieVO = args.movieVO
        binding.movieDetailPoster.transitionName = movieVO.id.toString()
        binding.movie = movieVO

        setupView()
        sharedElementEnterTransition = MaterialContainerTransform()
    }

    private fun setupView() {
        binding.movieDetailRating.text = movieVO.rating.toString()
        binding.rating.rating = movieVO.rating / 2

        val releaseDateFormatted = Definitions.formatReleaseDate(resources, movieVO.releaseDate)
        if (!releaseDateFormatted.isEmpty()) {
            binding.movieDetailReleaseDate.text = releaseDateFormatted
        } else {
            binding.movieDetailReleaseDate.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        }
    }
}