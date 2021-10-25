package com.brunacarbonesi.apps.estudomvvmroomretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brunacarbonesi.apps.estudomvvmroomretrofit.databinding.ListItemMovieBinding
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO

class MovieListAdapter() : RecyclerView.Adapter<MovieListAdapter.MovieListItemViewHolder>() {

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

        lateinit var listener: OnClickListener
        private val itemsLimit: Int = 18

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListItemMovieBinding = ListItemMovieBinding.inflate(inflater, parent, false)
        return MovieListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListItemViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        if (movies.size > itemsLimit) {
            return itemsLimit
        } else {
            return movies.size
        }
    }

    inner class MovieListItemViewHolder(
        val binding: ListItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind (movie: MovieVO) {
            binding.movie = movie
            binding.startView.transitionName = movie.id.toString()

            binding.root.setOnClickListener{
                listener.onClick(movie, binding.startView)
            }

            binding.executePendingBindings()
        }
    }
}
