package com.example.paginationwithrecycler

import retrofit2.Call

interface RetroHelper {
    fun getDatabyPage(page:String ,totalcount:String) : Call<DataModel>

}