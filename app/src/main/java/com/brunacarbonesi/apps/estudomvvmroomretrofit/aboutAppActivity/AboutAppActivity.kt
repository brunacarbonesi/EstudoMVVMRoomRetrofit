package com.brunacarbonesi.apps.estudomvvmroomretrofit.aboutAppActivity

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.brunacarbonesi.apps.estudomvvmroomretrofit.R
import com.brunacarbonesi.apps.estudomvvmroomretrofit.databinding.ActivityAboutAppBinding

class AboutAppActivity : AppCompatActivity() {
    private var _binding: ActivityAboutAppBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityAboutAppBinding.inflate(layoutInflater)

        getVersionApp()
        setupActionBar()

        setContentView(binding.root)
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.activityAboutAppToolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getVersionApp() {
        val manager = this.packageManager
        val info = manager.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            binding.activityAboutAppVersion.text =
                getString(R.string.text_version_app, "${info.longVersionCode}.${info.versionName}")
        } else {
            binding.activityAboutAppVersion.text = "versão ${info.versionCode}.${info.versionName}"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}