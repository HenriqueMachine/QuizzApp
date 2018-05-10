package com.example.henrique.kotlinapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

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

        btnAnswer.setOnClickListener {

            radioButtonA.setText("Resposta A. ?")
            radioButtonB.setText("Resposta B. ?")
            radioButtonC.setText("Resposta C. ?")
            txtViewQuizz.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam iaculis tincidunt tortor id consequat. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;\n Proin placerat rhoncus eleifend. Mauris purus tellus, fringilla ac risus ut, sodales posuere leo. Nullam faucibus lectus sit amet rutrum cursus. Aliquam mollis pharetra scelerisque. Aenean condimentum finibus nunc. Cras tempus nisi ut massa pretium, ut lacinia leo gravida. Nullam congue porttitor arcu, non aliquam tellus convallis ut.\n Ut imperdiet tellus at arcu vestibulum, et congue purus condimentum. Praesent ut tempor lectus. Duis rhoncus quis odio vel pulvinar. Donec vel purus felis. Aliquam.")
            btnAnswer.setText("CONFIRMAR")
        }

    }
}
