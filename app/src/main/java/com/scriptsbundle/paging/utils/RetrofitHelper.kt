package com.scriptsbundle.paging.utils


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    private  val Baseurl="https://api.stackexchange.com/"


    val retrofit = Retrofit.Builder()
        .baseUrl(Baseurl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}