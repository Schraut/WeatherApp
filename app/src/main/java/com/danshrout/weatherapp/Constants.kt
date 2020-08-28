package com.danshrout.weatherapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Constants {

    //My API Key from openweathermap.org
    const val APP_ID: String = "90ea4da22de2af50ded2f309786a23b0"

    //Base URL
    const val BASE_URL: String = "https://api.openweathermap.org/data/"

    //Metric unit, it takes care of celsius and fahrenheit
    const val METRIC_UNIT: String = "metric"

    fun isNetworkAvailable(context: Context) : Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        //Check to see if it's a new phone
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else { //For the older phones
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }
}