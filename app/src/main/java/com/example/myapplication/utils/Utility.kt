package com.example.myapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


fun isInternetConnectionAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
    return networkCapabilities?.let {
        when {
            it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } ?: false
}
