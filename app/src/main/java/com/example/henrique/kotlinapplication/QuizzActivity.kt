package com.example.henrique.kotlinapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.*
import kotlin.concurrent.timer

class QuizzActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz)

        val txtViewQuizz = findViewById(R.id.textViewQuizz) as TextView
        val radioGroupABC = findViewById(R.id.radioGroup) as RadioGroup
        val radioButtonA = findViewById(R.id.radioButtonA) as RadioButton
        val radioButtonB = findViewById(R.id.radioButtonB) as RadioButton
        val radioButtonC = findViewById(R.id.radioButtonC) as RadioButton
        val txtViewQuestion = findViewById(R.id.textViewQuestion) as TextView
        val btnAnswer = findViewById(R.id.btnAnswer) as Button
        val countTime = findViewById(R.id.chronometer) as Chronometer

        btnAnswer.setOnClickListener {

            radioButtonA.setText("Resposta A. ?")
            radioButtonB.setText("Resposta B. ?")
            radioButtonC.setText("Resposta C. ?")
            txtViewQuestion.setText("")
            btnAnswer.setText("CONFIRMAR")

        }
        fun random(a:Int, b:Int ): Int {

            return a + b
        }
        }

    }

