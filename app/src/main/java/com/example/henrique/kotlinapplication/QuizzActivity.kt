package com.example.henrique.kotlinapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.henrique.kotlinapplication.fragments.FragmentPlus

class QuizzActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, FragmentPlus())
                .commit()

    }

}

