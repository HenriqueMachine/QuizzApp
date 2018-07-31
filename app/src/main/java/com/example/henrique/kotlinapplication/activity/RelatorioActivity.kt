package com.example.henrique.kotlinapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.henrique.kotlinapplication.R

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

    }
}
