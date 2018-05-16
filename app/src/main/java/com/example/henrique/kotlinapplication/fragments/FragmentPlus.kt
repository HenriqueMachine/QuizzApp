package com.example.henrique.kotlinapplication.fragments


import android.app.Dialog
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.example.henrique.kotlinapplication.R
import com.example.henrique.kotlinapplication.models.Resposta
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*
import kotlin.collections.ArrayList


class FragmentPlus : Fragment() {

    private val randomNumber = Random()
    private val DIFICULTY = 100
    private val DIFICULTY_DIV = 10
    private val DIFICULTY_MULTI = 1000
    private var value1: Int? = null
    private var value2: Int? = null
    private var answer: Int? = null
    private var myAnswer: Int? = null
    private var myAnswer2: Int? = null
    private var init = false
    private var op = "+"
    private var timer: CountDownTimer? = null
    private var timerNow: Long? = null
    private var listAnswers = ArrayList <Resposta> ()
    private var questionsCorrect: Int? = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        op = arguments!!.getString("OP","")

        when (op){
            "+" -> text_op.text = "+"
            "-" -> text_op.text = "-"
            "*" -> text_op.text = "*"
            "/" -> text_op.text = "/"
        }

        init = false
        radioButtonA.isEnabled = false
        radioButtonB.isEnabled = false
        radioButtonC.isEnabled = false
        radioButtonD.isEnabled = false


        initViews()

    }

    override fun onResume() {
        super.onResume()
        if (init){
            radioGroup.clearCheck()
            startTimer(timerNow!!)
            radioButtonA.isEnabled = true
            radioButtonB.isEnabled = true
            radioButtonC.isEnabled = true
            radioButtonD.isEnabled = true


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
        timer = null
    }

    override fun onPause() {
        super.onPause()

        timer?.cancel()
    }

    private fun initViews() {

        radioButtonA.isEnabled = false
        radioButtonB.isEnabled = false
        radioButtonC.isEnabled = false
        radioButtonD.isEnabled = false

        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

                try {

                    myAnswer = radioGroup.findViewById<RadioButton>(checkedId).text.toString().toInt()

                }catch (e: Exception){
                    //Erro no divide
                    //myAnswer = 0
                }

            }

        })

        btnAnswer!!.setOnClickListener {

            if (!init) {
                btnAnswer!!.text = "CONFIRMAR"
                generateQuestion()
                startTimer(21000)
                init = true
                radioButtonA.isEnabled = true
                radioButtonB.isEnabled = true
                radioButtonC.isEnabled = true
                radioButtonD.isEnabled = true


            }else{
                verifyAnswer()
            }

        }

    }

    private fun startTimer(timer_milli:Long) {

        if (timer!=null){
            timer?.cancel()
            timer = null
        }

        timer = object :CountDownTimer(timer_milli,1000){

            override fun onFinish() {

                finishGame()
            }

            override fun onTick(millisUntilFinished: Long) {
                    timerNow = millisUntilFinished
                    timerToEnd.text = (millisUntilFinished/1000).toInt().toString()

            }

        }

        timer?.start()
    }

    private fun finishGame() {

        for (p in listAnswers){
            Log.i("LOG",p.toString())
        }
        timerToEnd.text = "0"
        Toast.makeText(activity,"Seu tempo acabou ='(", Toast.LENGTH_SHORT).show()
        showDialogResult()
    }

    private fun showDialogResult() {

        var dialog = Dialog(activity)

        dialog.setContentView(R.layout.custom_dialog)
        dialog.findViewById<Button>(R.id.btnPlayAgain).setOnClickListener {
            dialog.hide()
            questionsCorrect = 0
            initViews()
            generateQuestion()
            radioButtonA.isEnabled = true
            radioButtonB.isEnabled = true
            radioButtonC.isEnabled = true
            radioButtonD.isEnabled = true

            startTimer(21000)

        }
        dialog.findViewById<Button>(R.id.btnResult).setOnClickListener {  }

        dialog.findViewById<TextView>(R.id.textview_acertos).text = questionsCorrect.toString()
        dialog.show()

    }

    private fun verifyAnswer() {

        if (myAnswer == answer || myAnswer2 == answer){
            questionsCorrect = questionsCorrect?.plus(1)
            listAnswers.add(Resposta(value1!!,value2!!, answer!!,op,true))


            Toast.makeText(activity,"Acertou", Toast.LENGTH_SHORT).show()

            generateQuestion()
            startTimer((timerNow!! + 2000))

        }else{

            listAnswers.add(Resposta(value1!!,value2!!, answer!!,op,false))

            if (timerNow!! > 2000) {
                generateQuestion()
                Toast.makeText(activity,"Errou", Toast.LENGTH_SHORT).show()
                startTimer((timerNow!! - 2000))
            }else{
                timerToEnd.text = "0"
                finishGame()
                timer?.cancel()
            }

        }

        radioGroup.clearCheck()
    }

    private fun generateQuestion() {

        value1 = randomNumberGenerator(DIFICULTY)
        number1.text = value1.toString()
        value2 = randomNumberGenerator(DIFICULTY)
        number2.text = value2.toString()

        when(op){
            "+" -> answer = value1!! + value2!!
            "-" -> answer = value1!! - value2!!
            "*" -> answer = value1!! * value2!!
            "/" -> answer = value1!! / value2!!
        }

        when(op){

            "/" -> {genDificulty(DIFICULTY_DIV) }
            "*" -> {genDificulty(DIFICULTY_MULTI)}
            else -> {val position = randomGeneratorBtn(3)

                (radioGroup.getChildAt(0) as RadioButton).text = randomNumberGenerator(DIFICULTY).toString()
                (radioGroup.getChildAt(1) as RadioButton).text = randomNumberGenerator(DIFICULTY).toString()
                (radioGroup.getChildAt(2) as RadioButton).text = randomNumberGenerator(DIFICULTY).toString()
                (radioGroup.getChildAt(3) as RadioButton).text = randomNumberGenerator(DIFICULTY).toString()

                (radioGroup.getChildAt(position) as RadioButton).text = answer.toString()}

        }

    }

    private fun randomGeneratorBtn(buttonRandom: Int): Int {
        return randomNumber.nextInt(buttonRandom)
    }

    private fun randomNumberGenerator(dificulty:Int): Int{
        return randomNumber.nextInt(dificulty) + 5
    }

    private fun genDificulty(receive: Int){

        val position = randomGeneratorBtn(3)

        (radioGroup.getChildAt(0) as RadioButton).text = randomNumberGenerator(receive).toString()
        (radioGroup.getChildAt(1) as RadioButton).text = randomNumberGenerator(receive).toString()
        (radioGroup.getChildAt(2) as RadioButton).text = randomNumberGenerator(receive).toString()
        (radioGroup.getChildAt(3) as RadioButton).text = randomNumberGenerator(receive).toString()

        (radioGroup.getChildAt(position) as RadioButton).text = answer.toString()

    }

}
