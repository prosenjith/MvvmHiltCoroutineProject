package com.example.mvvmhiltcoroutineproject.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object ConnectivityHelper {
    fun isConnectedToInternet(context: Context): Boolean {
        val capabilities = getNetworkCapabilities(context)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
    }

    fun isConnectedToWifi(context: Context): Boolean {
        val capabilities = getNetworkCapabilities(context)
        return capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false
    }

    private fun getNetworkCapabilities(context: Context): NetworkCapabilities? {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val activeNetwork = connectivityManager?.activeNetwork
        return connectivityManager?.getNetworkCapabilities(activeNetwork)
    }
}