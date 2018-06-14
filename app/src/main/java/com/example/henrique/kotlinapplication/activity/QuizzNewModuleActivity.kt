package com.example.henrique.kotlinapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.henrique.kotlinapplication.R
import com.example.henrique.kotlinapplication.fragments.FragmentEquacao1grau

class QuizzNewModuleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_new_module)

        var fragment = FragmentEquacao1grau()

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container2, fragment)
                .commit()

    }
}
