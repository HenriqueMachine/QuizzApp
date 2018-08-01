package com.example.henrique.kotlinapplication.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.henrique.kotlinapplication.R
import com.example.henrique.kotlinapplication.utils.CustomDialog
import kotlinx.android.synthetic.main.toolbar_resultado.*

class RelatorioActivity : AppCompatActivity() {

    private var TOTAL:Int? = 0
    private var CERTO:Int? = 0
    private var ERRADO:Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relatorio)

        TOTAL = intent.extras?.getInt("TOTAL",0)
        CERTO = intent.extras?.getInt("CORRECT",0)
        ERRADO = intent.extras?.getInt("WRONG",0)

        initViews()

    }

    private fun initViews() {
        dialogRespostas()
    }

    override fun onBackPressed() {
        super.onBackPressed()



    }

    fun dialogRespostas(){

        imageview_seta_voltar.setOnClickListener {
            CustomDialog(this).showDialogRelatorio("Atenção","Para onde você deseja ir?", object : CustomDialog.CustomDialogActionsEndGame{
                override fun playAgain() {

                    //questoes

                }

                override fun resultado() {

                    //Menu

                }

                override fun relatorio() {

                    //jogarNovamente

                }
            })
        }}

}
