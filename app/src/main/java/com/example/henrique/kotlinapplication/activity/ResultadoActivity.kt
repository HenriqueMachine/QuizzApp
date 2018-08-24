package com.example.henrique.kotlinapplication.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.example.henrique.kotlinapplication.R
import com.example.henrique.kotlinapplication.adapters.RecyclerAdapterResposta
import com.example.henrique.kotlinapplication.models.Resposta
import com.example.henrique.kotlinapplication.utils.CustomDialog
import kotlinx.android.synthetic.main.activity_resultado.*
import kotlinx.android.synthetic.main.toolbar_resultado.*

class ResultadoActivity : AppCompatActivity() {

    private var adapter: RecyclerAdapterResposta? = null

    private var listRespostas : ArrayList<Resposta>? = null

    private var op = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        listRespostas = intent.extras?.getParcelableArrayList<Resposta>("RESPOSTAS")

        op = intent.extras?.getString("OP","").toString()

        initViews()

    }

    private fun initViews() {

        adapter = RecyclerAdapterResposta(ArrayList(),this)

        recyclerview_resultado.layoutManager = LinearLayoutManager(this)

        recyclerview_resultado.adapter = adapter


        if (listRespostas != null) {
            adapter?.replaceData(listRespostas!!)
        }

        dialogRespostas()

    }

    override fun onBackPressed() {
        super.onBackPressed()

//        var intent = Intent(this@ResultadoActivity, QuizzActivity::class.java )
//        intent.putExtra("OP",op)
//        startActivity(intent)

    }

    fun dialogRespostas(){

        imageview_seta_voltar.setOnClickListener {
            CustomDialog(this).showDialogResposta(
                    "Atenção",
                    "Para onde você deseja ir?",
                    object: CustomDialog.CustomDialogActions{

                        override fun yes() {

                            var intent = Intent(this@ResultadoActivity, QuizzActivity::class.java )
                            intent.putExtra("OP",op)
                            startActivity(intent)
                            finish()
//                           onBackPressed()
                        }

                        override fun no() {

                            var intent = Intent(this@ResultadoActivity, MainActivity::class.java )
                            startActivity(intent)
                            finish()

                        }
                    })
        }}

}
