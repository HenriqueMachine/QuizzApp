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
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

import com.example.henrique.kotlinapplication.R
import com.example.henrique.kotlinapplication.activity.MainActivity
import com.example.henrique.kotlinapplication.models.Resposta
import kotlinx.android.synthetic.main.fragment_equacao_1_grau.*
import java.util.concurrent.ThreadLocalRandom

class FragmentEquacao1grau : Fragment() {

    private var value1: Int? = null
    private var value2: Int? = null
    private var valueAuxOp: Int? = null
    private var answer: Int? = null
    private var myAnswer: Int? = null
    private var init = false
    private var op = ""
    private var timer: CountDownTimer? = null
    private var timerNow: Long? = null
    private var listAnswers = ArrayList <Resposta> ()
    private var questionsCorrect: Int? = 0
    private  var dialog: Dialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equacao_1_grau, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSairOther.setOnClickListener {
            val intent = Intent (activity, MainActivity:: class.java)
            startActivity(intent)
        }

        init = false
        radioButtonAother.isEnabled = false
        radioButtonBother.isEnabled = false
        radioButtonCother.isEnabled = false

        dialog = Dialog(activity)

        initViews()

    }

    override fun onResume() {
        super.onResume()
        listAnswers.clear()
        if (init){
            radioGroupOther.clearCheck()
            startTimer(timerNow!!)
            radioButtonAother.isEnabled = true
            radioButtonBother.isEnabled = true
            radioButtonCother.isEnabled = true

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
                timerToEndOther.text = (millisUntilFinished/1000).toInt().toString()

            }

        }

        timer?.start()
    }

    private fun finishGame() {

        for (p in listAnswers){
            Log.i("LOG",p.toString())
        }
        timerToEndOther.text = "0"
        showDialogResult()
    }

    private fun showDialogResult() {

        dialog?.setContentView(R.layout.custom_dialog)
        dialog?.setCancelable(false)
        dialog?.findViewById<Button>(R.id.btnPlayAgain)?.setOnClickListener {

            questionsCorrect = 0
            radioGroupOther.clearCheck()
            initViews()
            generateQuestion()
            radioButtonAother.isEnabled = true
            radioButtonBother.isEnabled = true
            radioButtonCother.isEnabled = true
            when (op){
                "/" -> {startTimer(61000)}
                "*" -> {startTimer(61000)}
                else ->{startTimer(61000)}
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
                    ?.replace(R.id.container2, resultadoFragment)
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
            when (op){
                "+" -> {startTimer(timerNow!! + 11000)}
                "-" -> {startTimer(timerNow!! + 11000)}

            }
        }
        if (myAnswer != answer ){
            listAnswers.add(Resposta(value1!!,value2!!, answer!!,op,false))
            when (op){
                "+" -> {startTimer(timerNow!! - 5100)
                    if (timerNow!! > 5100){
                        generateQuestion()
                    }else {timerToEndOther.text = "0"
                        finishGame()
                        timer?.cancel()} }

                "-" -> {startTimer(timerNow!! - 5100)
                    if (timerNow!! > 5100){
                        generateQuestion()
                    }else {timerToEndOther.text = "0"
                        finishGame()
                        timer?.cancel()}}
            }

        }

        generateQuestion()

        radioGroupOther.clearCheck()
    }

    private fun initViews() {

        radioButtonAother.isEnabled = false
        radioButtonBother.isEnabled = false
        radioButtonCother.isEnabled = false

        radioGroupOther.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

                try {

                    myAnswer = radioGroupOther.findViewById<RadioButton>(checkedId).text.toString().toInt()

                }catch (e: Exception){
                    //Erro no divide
                    //myAnswer = 0
                }

            }

        })

        //Botão confirmar
        btnAnswerOther!!.setOnClickListener {

            if (!init) {
                btnAnswerOther!!.text = "CONFIRMAR"
                generateQuestion()
                init = true
                radioButtonAother.isEnabled = true
                radioButtonBother.isEnabled = true
                radioButtonCother.isEnabled = true
                when (op){
                    "/" -> {startTimer(61000)}
                    "*" -> {startTimer(61000)}
                    else ->{startTimer(61000)}
                }

            }else{
                verifyAnswer()
            }

        }

    }

   private fun generateQuestion() {

        value1 = randomNumberGenerator(6, 52)
        number1others.text = value1.toString()

        value2 = randomNumberGenerator(7, 51)
        number2others.text = value2.toString()

        valueAuxOp = randomNumberGenerator(0, 100)

       if(valueAuxOp!!.mod(2) == 0){
           op = "+"
           text_op_x.text = "+"
           if (text_op_x.text == "+") {
               answer = value2!! - value1!!
           }
       }else{
           op = "-"
           text_op_x.text = "-"
           if (text_op_x.text == "-") {
               answer =  value2!! + value1!!
           }
       }

        val position = randomNumberGenerator(0, 2)

        (radioGroupOther.getChildAt(0) as RadioButton).text = randomNumberGenerator((answer!! - 22), (answer!! + 22)).toString()
        (radioGroupOther.getChildAt(1) as RadioButton).text = randomNumberGenerator((answer!! - 22), (answer!! + 22)).toString()
        (radioGroupOther.getChildAt(2) as RadioButton).text = randomNumberGenerator((answer!! - 22), (answer!! + 22)).toString()

        (radioGroupOther.getChildAt(position) as RadioButton).text = answer.toString()
    }

    }

private fun randomNumberGenerator(min:Int, max:Int): Int{
    var inteiro:Int = ThreadLocalRandom.current().nextInt(min, max)
    return inteiro
}
