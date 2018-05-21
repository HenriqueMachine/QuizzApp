package com.example.henrique.kotlinapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.henrique.kotlinapplication.R
import com.example.henrique.kotlinapplication.fragments.FragmentBasicOperation

class QuizzActivity : AppCompatActivity() {



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

}

