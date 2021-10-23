package com.brunacarbonesi.apps.estudomvvmroomretrofit.aboutAppActivity

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.brunacarbonesi.apps.estudomvvmroomretrofit.R
import com.brunacarbonesi.apps.estudomvvmroomretrofit.databinding.ActivityAboutAppBinding

class AboutAppActivity : AppCompatActivity() {
    private var _binding: ActivityAboutAppBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityAboutAppBinding.inflate(layoutInflater)


        setContentView(binding.root)

        setupViews()

        getVersionApp()

    }

    private fun setupViews() {
        setSupportActionBar(binding.activityAboutAppToolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun getVersionApp() {
        val manager = this.packageManager
        val info = manager.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES)

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            binding.activityAboutAppVersion.text = getString(R.string.text_version_app,"${info.longVersionCode}.${info.versionName}")
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