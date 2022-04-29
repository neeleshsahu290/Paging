package com.scriptsbundle.paging.utils

import com.scriptsbundle.paging.modelclass.Answers
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("/2.3/answers")
    suspend fun getanswers(
        @Query("page") page: Int ,
        @Query("pagesize") pagesize :Int ,
        @Query("order") order:String ,
        @Query("sort") sort:String,
        @Query("site") site:String
    ): Response<Answers>



}