package com.brunacarbonesi.apps.estudomvvmroomretrofit.utils

import android.content.res.Configuration
import android.content.res.Resources
import android.util.Log
import com.brunacarbonesi.apps.estudomvvmroomretrofit.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Definitions {
    fun numberOfColumnsDefinition(resources: Resources): Int {
        val variableNumberOfColumns: Int =
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                6
            } else {
                3
            }
        return variableNumberOfColumns
    }

    fun formatReleaseDate(resources: Resources, date: String): String {
        val releaseDate: String
        val outputText: String
        val outputFormat: DateFormat = SimpleDateFormat("MM/yyyy", Locale.US)
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        val dateReceived: Date? = inputFormat.parse(date)
        if (dateReceived != null) {
            outputText = outputFormat.format(dateReceived)
            releaseDate = resources.getString(R.string.release_date, outputText)
        } else {
            releaseDate = ""
        }
        return releaseDate
    }
}