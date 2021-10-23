package com.brunacarbonesi.apps.estudomvvmroomretrofit.adapter

import androidx.recyclerview.widget.RecyclerView
import com.brunacarbonesi.apps.estudomvvmroomretrofit.databinding.ListItemMovieBinding
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO

class MovieListItemViewHolder(
    val binding: ListItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind (movie: MovieVO) {
        binding.movie = movie
        binding.executePendingBindings()
    }
}