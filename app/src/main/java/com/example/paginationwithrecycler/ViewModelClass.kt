package com.example.paginationwithrecycler

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paginationwithrecycler.databinding.ActivityMainBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class ViewModelClass @Inject constructor(val repositoryImpl: RetroHelper) : ViewModel() {
    var data = MutableLiveData<DataModel>()
    val datatemp: LiveData<DataModel>
    get() = data


    fun getData(context: Activity, page: String, totalcount: String) {
        val progress=context.findViewById<ProgressBar>(R.id.progressbar)
        progress.visibility=View.VISIBLE
        viewModelScope.launch {
            repositoryImpl.getDatabyPage(page, totalcount).enqueue(object : Callback<DataModel?> {
                override fun onResponse(call: Call<DataModel?>, response: Response<DataModel?>) {
                    if (response.isSuccessful){
                        data.postValue(response.body())
                        progress.visibility=View.GONE
                }else{
                        Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT)
                            .show()
                        progress.visibility=View.GONE
                    }
                }
                override fun onFailure(call: Call<DataModel?>, t: Throwable) {
                    Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show()
                    progress.visibility=View.GONE
                }
            })
        }
    }

}
