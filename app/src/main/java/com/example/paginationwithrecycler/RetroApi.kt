package com.example.paginationwithrecycler

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroApi {

    @GET("passenger")
     fun getDataByPages(@Query ("page")page:String, @Query ("size")totalcount:String) : Call<DataModel>

}