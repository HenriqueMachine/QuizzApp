package com.example.henrique.kotlinapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart = findViewById(R.id.btnStart) as Button

        buttonStart.setOnClickListener {
            val intent = Intent (this, QuizzActivity :: class.java)
            startActivity(intent)
            text_op.text = "+"
        }

        btnMinus.setOnClickListener {
            val intent = Intent(this, QuizzActivity ::class.java)
            startActivity(intent)
            text_op.text = "-"
        }
        btnX.setOnClickListener { val intent = Intent(this, QuizzActivity ::class.java)
            startActivity(intent)
            text_op.text = "*"
        }
        btnDivide.setOnClickListener { val intent = Intent(this, QuizzActivity ::class.java)
            startActivity(intent)
            text_op.text = "/"
        }

    }
}
