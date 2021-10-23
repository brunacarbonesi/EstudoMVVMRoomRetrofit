package com.brunacarbonesi.apps.estudomvvmroomretrofit.splashActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.brunacarbonesi.apps.estudomvvmroomretrofit.R
import com.brunacarbonesi.apps.estudomvvmroomretrofit.movieslistActivity.MoviesListActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent =  Intent(this@SplashActivity, MoviesListActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }
}