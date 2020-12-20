package com.shanu.nytimesarticle.retrofit.base;

import com.shanu.nytimesarticle.BuildConfig
import com.shanu.nytimesarticle.retrofit.api.ApiInterface
import com.shanu.nytimesarticle.retrofit.interceptor.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitNetwork {

    companion object {

        private const val NETWORK_CALL_TIMEOUT = 60
        private const val BASE_URL = "http://api.nytimes.com/";
        //You can add cache history below are the params
        // @application.cacheDir,
        // @10 * 1024 * 1024  (10MB)
        fun retrofit(networkConnectionInterceptor: NetworkConnectionInterceptor): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder()
                    .addInterceptor(networkConnectionInterceptor)
                    .addInterceptor(HttpLoggingInterceptor()
                        .apply {
                            level = if (BuildConfig.DEBUG)
                                HttpLoggingInterceptor.Level.BODY
                            else
                                HttpLoggingInterceptor.Level.NONE
                        })
                    .readTimeout(BuildConfig.READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(BuildConfig.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(BuildConfig.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        //Add Whatever service instances needed
        fun getApiInterface (networkConnectionInterceptor: NetworkConnectionInterceptor): ApiInterface {
            return retrofit(networkConnectionInterceptor).create(ApiInterface::class.java)
        }

    }
}