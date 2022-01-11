package com.example.paginationwithrecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationwithrecycler.databinding.ItemlayoutBinding

class RecyclerAdapterClass(val list :ArrayList<DataModel.dataarray>,val context: Context) : RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>() {

   class ViewHolderClass(val view1: ItemlayoutBinding) : RecyclerView.ViewHolder(view1.root){
    fun bind(data:DataModel.dataarray)
    {
        view1.itemdata=data.airline.get(0)

    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val view=ItemlayoutBinding.inflate(LayoutInflater.from(context))
          return  ViewHolderClass(view)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
    holder.bind(list.get(position))
    }

    override fun getItemCount(): Int {
       return  list.size
    }


}


