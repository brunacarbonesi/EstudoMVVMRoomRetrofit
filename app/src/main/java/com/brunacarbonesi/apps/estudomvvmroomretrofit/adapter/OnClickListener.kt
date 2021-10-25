package com.brunacarbonesi.apps.estudomvvmroomretrofit.adapter

import android.widget.ImageView
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO

interface OnClickListener {
    fun onClick(movieVO: MovieVO, imageView: ImageView)
}