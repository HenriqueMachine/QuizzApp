package com.example.henrique.kotlinapplication.utils

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.TextView
import com.example.henrique.kotlinapplication.R

open class CustomDialog (var context:Context?){

    var dialog:Dialog? = null

    fun showDialog(tittle:String, body:String,listern:CustomDialogActions){

        dialog = Dialog(context)
        dialog?.setContentView(R.layout.custom_dialog_util)
        dialog?.setCancelable(false)
        dialog?.findViewById<TextView>(R.id.textview_tittle_dialog_util)?.text = tittle
        dialog?.findViewById<TextView>(R.id.textview_corpo_mensagem)?.text = body
        dialog?.findViewById<Button>(R.id.button_sim)?.setOnClickListener {
            dialog?.dismiss()
            listern?.yes()

        }
        dialog?.findViewById<Button>(R.id.button_nao)?.setOnClickListener {
            dialog?.dismiss()
            listern?.no()

        }

        dialog?.show()

    }

    interface CustomDialogActions{
        fun yes()
        fun no()
    }

}