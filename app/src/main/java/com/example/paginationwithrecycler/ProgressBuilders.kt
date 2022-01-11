package com.example.paginationwithrecycler

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable


object ProgressBuilders {
   lateinit var progress:Dialog
    fun show(context: Context, text:String)
    {
        progress = Dialog(context)
        progress.setContentView(R.layout.progresslayout)
        progress.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progress.setTitle(text)
        progress.show()

    }
    fun dismiss()
    {
        progress.dismiss()
    }
}