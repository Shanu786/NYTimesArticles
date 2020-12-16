package com.shanu.nytimesarticle.retrofit.interceptor

import android.content.Context
import android.net.ConnectivityManager
import com.shanu.nytimesarticle.R
import com.shanu.nytimesarticle.utils.ApiException
import com.shanu.nytimesarticle.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        if (!isInternetAvailable())
            throw NoInternetException(applicationContext.getString(R.string.connection_unavailable_desc))

        val response = chain.proceed(request)
        if (response.code() == 500)
            throw ApiException(applicationContext.getString(R.string.connection_unavailable_desc))

        return response
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }

}