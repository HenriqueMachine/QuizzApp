package com.example.henrique.kotlinapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart = findViewById(R.id.btnStart) as Button
        val buttonStartFrag = findViewById(R.id.btnStartFragment) as Button

        buttonStart.setOnClickListener {
            val intent = Intent (this, QuizzActivity :: class.java)
            startActivity(intent)
        }

        buttonStartFrag.setOnClickListener {


        }

    }
}
