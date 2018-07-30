package com.example.henrique.kotlinapplication.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.henrique.kotlinapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart = findViewById<Button>(R.id.btnStart)

        buttonStart.setOnClickListener {
            val intent = Intent (this, QuizzActivity:: class.java)
            intent.putExtra("OP","+")
            startActivity(intent)
        }

        btnMinus.setOnClickListener {
            val intent = Intent(this, QuizzActivity::class.java)
            intent.putExtra("OP","-")
            startActivity(intent)
        }
        btnX.setOnClickListener {
            val intent = Intent(this, QuizzActivity::class.java)
            intent.putExtra("OP","*")
            startActivity(intent)
        }
        btnDivide.setOnClickListener {
            val intent = Intent(this, QuizzActivity::class.java)
            intent.putExtra("OP","/")
            startActivity(intent)
        }

    }
}
