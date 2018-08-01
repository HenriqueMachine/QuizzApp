package com.example.henrique.kotlinapplication.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.henrique.kotlinapplication.R

open class CustomDialog (val context:Context?){

    var dialog:Dialog? = null
    var dialogEnd:Dialog? = null

    fun showDialog(tittle:String, body:String,listern:CustomDialogActions){

        if (dialog == null){

            dialog = Dialog(context)

        }

        dialog?.setContentView(R.layout.custom_dialog_util)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)
        dialog?.findViewById<TextView>(R.id.textview_tittle_dialog_endgame)?.text = tittle
        dialog?.findViewById<TextView>(R.id.textview_corpo_mensagem)?.text = body
        dialog?.findViewById<Button>(R.id.button_sim)?.setOnClickListener {

            listern.yes()
            dialog?.dismiss()

        }
        dialog?.findViewById<Button>(R.id.button_nao)?.setOnClickListener {

            listern.no()
            dialog?.dismiss()

        }

        if (dialog?.isShowing == false)
            dialog?.show()

    }

    fun showDialogEndGame(tittle:String, acertos:String, listern:CustomDialogActionsEndGame){

        if (dialogEnd == null){

            dialogEnd = Dialog(context)

        }

        dialogEnd?.setContentView(R.layout.custom_dialog)
        dialogEnd?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogEnd?.setCancelable(false)
        dialogEnd?.findViewById<TextView>(R.id.textview_tittle_dialog_endgame)?.text = tittle
        dialogEnd?.findViewById<TextView>(R.id.textview_acertos)?.text = acertos
        dialogEnd?.findViewById<Button>(R.id.buttonPlayAgain)?.setOnClickListener {
            listern.playAgain()
            dialogEnd?.dismiss()

        }
        dialogEnd?.findViewById<Button>(R.id.buttonResult)?.setOnClickListener {
            listern.resultado()
            dialogEnd?.dismiss()

        }
        dialogEnd?.findViewById<Button>(R.id.buttonRelatorio)?.setOnClickListener {
            listern.relatorio()
            dialogEnd?.dismiss()

        }

        if (dialogEnd?.isShowing == false)
            dialogEnd?.show()

    }

    fun showDialogResposta(tittle:String, body:String,listern:CustomDialogActions){

        if (dialog == null){

            dialog = Dialog(context)

        }

        dialog?.setContentView(R.layout.custom_dialog_resposta)
        dialog?.findViewById<Button>(R.id.button_questoes_resposta)?.visibility = View.GONE
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)
        dialog?.findViewById<TextView>(R.id.textview_tittle_dialog_resposta)?.text = tittle
        dialog?.findViewById<TextView>(R.id.textview_corpo_mensagem_resposta)?.text = body
        dialog?.findViewById<ImageView>(R.id.imageview_close_dialog)?.setOnClickListener{

            dialog?.dismiss()

        }
        dialog?.findViewById<Button>(R.id.button_sim_resposta)?.setOnClickListener {

            listern.yes()
            dialog?.dismiss()

        }
        dialog?.findViewById<Button>(R.id.button_nao_resposta)?.setOnClickListener {

            listern.no()
            dialog?.dismiss()

        }

        if (dialog?.isShowing == false)
            dialog?.show()

    }

    fun showDialogRelatorio(tittle:String, body:String,listern:CustomDialogActionsEndGame){

        if (dialog == null){

            dialog = Dialog(context)

        }

        dialog?.setContentView(R.layout.custom_dialog_resposta)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)
        dialog?.findViewById<TextView>(R.id.textview_tittle_dialog_resposta)?.text = tittle
        dialog?.findViewById<TextView>(R.id.textview_corpo_mensagem_resposta)?.text = body
        dialog?.findViewById<Button>(R.id.button_questoes_resposta)?.visibility = View.VISIBLE
        dialog?.findViewById<Button>(R.id.button_questoes_resposta)?.setOnClickListener {

            listern.playAgain()
            dialog?.dismiss()

        }
        dialog?.findViewById<ImageView>(R.id.imageview_close_dialog)?.setOnClickListener{

            dialog?.dismiss()

        }
        dialog?.findViewById<Button>(R.id.button_sim_resposta)?.setOnClickListener {

            listern.relatorio()
            dialog?.dismiss()

        }
        dialog?.findViewById<Button>(R.id.button_nao_resposta)?.setOnClickListener {

            listern.resultado()
            dialog?.dismiss()

        }

        if (dialog?.isShowing == false)
            dialog?.show()

    }

    interface CustomDialogActions{
        fun yes()
        fun no()
    }

    interface CustomDialogActionsEndGame{
        fun playAgain()
        fun resultado()
        fun relatorio()
    }

}