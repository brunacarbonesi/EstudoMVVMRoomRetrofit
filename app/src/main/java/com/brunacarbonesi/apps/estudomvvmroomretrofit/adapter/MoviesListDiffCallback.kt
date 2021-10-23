package com.brunacarbonesi.apps.estudomvvmroomretrofit.adapter

import androidx.recyclerview.widget.DiffUtil
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO

class MoviesListDiffCallback(
    private val oldList: List<MovieVO>,
    private val newList: List<MovieVO>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id.equals(newList[newItemPosition].id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}