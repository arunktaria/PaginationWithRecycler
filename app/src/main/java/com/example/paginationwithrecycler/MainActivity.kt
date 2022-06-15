package com.example.paginationwithrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationwithrecycler.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
 lateinit var list: ArrayList<DataModel.dataarray>
 var pageno=1
  var TAG="TAG"
    var iscrolled=false
    lateinit var  layoutManager:LinearLayoutManager
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerAdapterClass: RecyclerAdapterClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list= arrayListOf()

        layoutManager=LinearLayoutManager(this)
        binding.recyclerview.layoutManager=layoutManager
        recyclerAdapterClass= RecyclerAdapterClass(list,this)
        binding.recyclerview.adapter=recyclerAdapterClass

        val viewmodel=ViewModelProvider(this).get(ViewModelClass::class.java)


        viewmodel.getData(this@MainActivity, pageno.toString(), "10")


        viewmodel.data.observe(this, Observer {
           if(it!=null)
           {
               list.addAll(it.data)
               recyclerAdapterClass.notifyDataSetChanged()
           }else
               Toast.makeText(this,"there is no list at all",Toast.LENGTH_SHORT).show()
        })


        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

               if (newState==AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                   iscrolled=true

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val vitem=layoutManager.childCount
                val skipped=layoutManager.findFirstVisibleItemPosition()
                val totalitem=layoutManager.itemCount
                if (iscrolled && vitem+skipped==totalitem) {
                 pageno++  
                 viewmodel.getData(this@MainActivity, pageno.toString(), "10")             
                    Log.d(TAG, "onScrolled: "+pageno.toString())
                    iscrolled=false
                }

            }
        })


    }
}
