package com.brunacarbonesi.apps.estudomvvmroomretrofit.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.brunacarbonesi.apps.estudomvvmroomretrofit.R
import com.squareup.picasso.Picasso

@BindingAdapter(value=["setImageUrl"])
fun ImageView.bindImageUrl(url:String) {
    if (url.isNotBlank()) {
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w342$url")
            .error(R.drawable.placeholder_movie)
            .into(this)

    }
}