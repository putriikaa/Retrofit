package com.example.tugasretrofit.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun getInstance():ApiService{
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()

        // Pastikan URL ini sesuai dengan endpoint yang benar
        val builder = Retrofit.Builder()
            .baseUrl("http://demo.lazday.com/rest-api-sample/data.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()


        return builder.create(ApiService::class.java)
    }
}