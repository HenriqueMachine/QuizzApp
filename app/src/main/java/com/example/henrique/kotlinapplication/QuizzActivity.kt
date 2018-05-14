package com.example.henrique.kotlinapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.henrique.kotlinapplication.fragments.BlankFragment
import kotlinx.android.synthetic.main.activity_quizz.*
import java.util.*

class QuizzActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, BlankFragment())
                .commit()

    }

}

