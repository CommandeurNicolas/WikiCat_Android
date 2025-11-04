package com.nicolascommandeur.wikicat.data.remote.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nicolascommandeur.wikicat.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL: String = "https://api.thecatapi.com/v1/"

    private val gson: Gson by lazy {
        GsonBuilder().create()
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request: Request = chain.request()
                    .newBuilder()
                    .header("accept", "application/json")
                    .header("x-api-key", BuildConfig.API_KEY)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val theCatApi: TheCatApi by lazy {
        retrofit.create(TheCatApi::class.java)
    }
}