package com.example.henrique.kotlinapplication.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.ipiranga.abasteceai.Support.Util.AnimationsCustomUtil
import com.example.henrique.kotlinapplication.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        btnJogar.setOnClickListener {
            val intent = Intent (this, MainActivity:: class.java)
            startActivity(intent)
        }
        img.alpha = 0f
        AnimationsCustomUtil.startAnimationFadeInUp(img,500)

    }
}
