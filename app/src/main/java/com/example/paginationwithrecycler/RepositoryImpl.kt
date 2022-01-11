package com.example.paginationwithrecycler

import retrofit2.Call
import javax.inject.Inject


class RepositoryImpl @Inject constructor(val retroApi: RetroApi) : RetroHelper {
    override fun getDatabyPage(page: String, totalcount: String): Call<DataModel> {
        return retroApi.getDataByPages(page,totalcount)
    }

}
