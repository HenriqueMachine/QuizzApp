package com.example.henrique.kotlinapplication.fragments


import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.example.henrique.kotlinapplication.R
import com.example.henrique.kotlinapplication.activity.MainActivity
import com.example.henrique.kotlinapplication.activity.QuizzActivity
import com.example.henrique.kotlinapplication.models.Resposta
import kotlinx.android.synthetic.main.fragment_basic_operation.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.collections.ArrayList


class FragmentBasicOperation : Fragment() {

    //private val randomNumber = Random()
    private val DIFICULTY = 500
    private var value1: Int? = null
    private var value2: Int? = null
    private var answer: Int? = null
    private var myAnswer: Int? = null
    private var init = false
    private var op = "+"
    private var timer: CountDownTimer? = null
    private var timerNow: Long? = null
    private var listAnswers = ArrayList <Resposta> ()
    private var questionsCorrect: Int? = 0
    private  var dialog:Dialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_basic_operation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSair.setOnClickListener {

            val intent = Intent (activity, MainActivity:: class.java)
            startActivity(intent)

        }

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

        dialog = Dialog(activity)

        initViews()

    }

    override fun onResume() {
        super.onResume()
        listAnswers.clear()
        if (init){
            radioGroup.clearCheck()
            startTimer(timerNow!!)
            radioButtonA.isEnabled = true
            radioButtonB.isEnabled = true
            radioButtonC.isEnabled = true

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

        //Botão confirmar
        btnAnswer!!.setOnClickListener {

            if (!init) {
                btnAnswer!!.text = "CONFIRMAR"
                generateQuestion()
                init = true
                radioButtonA.isEnabled = true
                radioButtonB.isEnabled = true
                radioButtonC.isEnabled = true
                when (op){
                    "/" -> {startTimer(61000)}
                    "*" -> {startTimer(61000)}
                    else ->{startTimer(41000)}
                }

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
        showDialogResult()
    }

    private fun showDialogResult() {

        dialog?.setContentView(R.layout.custom_dialog)
        dialog?.setCancelable(false)
        dialog?.findViewById<Button>(R.id.btnPlayAgain)?.setOnClickListener {

            questionsCorrect = 0
            initViews()
            generateQuestion()
            radioButtonA.isEnabled = true
            radioButtonB.isEnabled = true
            radioButtonC.isEnabled = true
            when (op){
                "/" -> {startTimer(61000)}
                "*" -> {startTimer(61000)}
                else ->{startTimer(41000)}
            }
            dialog?.dismiss()
        }
        dialog?.findViewById<Button>(R.id.btnResult)?.setOnClickListener {



            var resultadoFragment = ResultadoFragment()

            var args = Bundle()
            args.putParcelableArrayList("RESPOSTAS",listAnswers)

            resultadoFragment.arguments = args

            activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.container, resultadoFragment)
                    ?.addToBackStack(null)
                    ?.commit()

            questionsCorrect = 0

            dialog?.dismiss()


        }

        dialog?.findViewById<TextView>(R.id.textview_acertos)?.text = questionsCorrect.toString()

        if (!dialog!!.isShowing)
            dialog?.show()

    }

    private fun verifyAnswer() {

        if (myAnswer == answer){
            questionsCorrect = questionsCorrect?.plus(1)
            listAnswers.add(Resposta(value1!!,value2!!, answer!!,op,true))
            generateQuestion()
            when (op){
                "/" -> startTimer(timerNow!! + 11000)
                "*" -> startTimer(timerNow!! + 11000)
                "+" -> startTimer(timerNow!! + 5000)
                "-" -> startTimer(timerNow!! + 5000)
            }
        }
         if (myAnswer != answer ){
            listAnswers.add(Resposta(value1!!,value2!!, answer!!,op,false))
             when (op){
                 "/" -> {startTimer(timerNow!! - 5100)
                 if (timerNow!! > 5100){
                     finishGame()
                 }else {timerToEnd.text = "0"
                     finishGame()
                     timer?.cancel()} }

                 "*" -> {startTimer(timerNow!! - 5100)
                     if (timerNow!! > 5100){
                         generateQuestion()
                     }else {timerToEnd.text = "0"
                         finishGame()
                         timer?.cancel()}}

                 "/" -> {startTimer(timerNow!! - 5100)
                     if (timerNow!! > 5100){
                         generateQuestion()
                     }else {timerToEnd.text = "0"
                         finishGame()
                         timer?.cancel()}}

                 "+" -> {startTimer(timerNow!! - 5100)
                     if (timerNow!! > 5100){
                         generateQuestion()
                     }else {timerToEnd.text = "0"
                         finishGame()
                         timer?.cancel()}}

                 "-" -> {startTimer(timerNow!! - 5100)
                     if (timerNow!! > 5100){
                         generateQuestion()
                     }else {timerToEnd.text = "0"
                         finishGame()
                         timer?.cancel()}}
             }

        }

        radioGroup.clearCheck()
    }

    private fun generateQuestion() {

        when (op){
            "*" -> {  value1 = randomNumberGenerator((4),94)
                number1.text = value1.toString()
                value2 = randomNumberGenerator((3),22)
                number2.text = value2.toString()}

            "/" -> {  value1 = randomNumberGenerator((9),DIFICULTY)
                number1.text = value1.toString()
                value2 = randomNumberGenerator((1),14)
                number2.text = value2.toString()}

            else -> {
                value1 = randomNumberGenerator((7),DIFICULTY)
                number1.text = value1.toString()
                value2 = randomNumberGenerator((8),DIFICULTY)
                number2.text = value2.toString()}
        }
        when(op){
            "+" -> answer = value1!! + value2!!
            "-" -> answer = value1!! - value2!!
            "*" -> answer = value1!! * value2!!
            "/" -> answer = value1!! / value2!!
        }

        val position = randomNumberGenerator(0,2)

        (radioGroup.getChildAt(0) as RadioButton).text = randomNumberGenerator((answer!! - 22),(answer!! + 22)).toString()
        (radioGroup.getChildAt(1) as RadioButton).text = randomNumberGenerator((answer!! - 22),(answer!! + 22)).toString()
        (radioGroup.getChildAt(2) as RadioButton).text = randomNumberGenerator((answer!! - 22),(answer!! + 22)).toString()

        (radioGroup.getChildAt(position) as RadioButton).text = answer.toString()}

}

private fun randomNumberGenerator(min:Int,numParaGerar:Int): Int{
    var inteiro:Int = ThreadLocalRandom.current().nextInt(min, numParaGerar)
    return inteiro
}



