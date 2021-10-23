package com.brunacarbonesi.apps.estudomvvmroomretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brunacarbonesi.apps.estudomvvmroomretrofit.databinding.ListItemMovieBinding
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO

class MovieListAdapter : RecyclerView.Adapter<MovieListItemViewHolder>() {

    var movies = emptyList<MovieVO>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                MoviesListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListItemMovieBinding = ListItemMovieBinding.inflate(inflater, parent, false)
        return MovieListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListItemViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}
