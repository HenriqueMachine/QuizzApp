package com.example.henrique.kotlinapplication.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.henrique.kotlinapplication.R
import com.example.henrique.kotlinapplication.fragments.FragmentBasicOperation
import com.example.henrique.kotlinapplication.utils.CustomDialog

class QuizzActivity : AppCompatActivity() {

    var mFragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz)

        var op = intent.extras.getString("OP")

        var fragment = FragmentBasicOperation()

        var args = Bundle()
        args.putString("OP",op)
        fragment.arguments = args

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit()


    }

    override fun onBackPressed() {

        CustomDialog(this).showDialog("Atenção", "Você deseja mesmo sair?", object: CustomDialog.CustomDialogActions{
            override fun yes() {

                onDestroy()
                val intent = Intent (this@QuizzActivity, MainActivity:: class.java)
                startActivity(intent)

            }

            override fun no() {

                onResume()

            }
        })

    }


}

