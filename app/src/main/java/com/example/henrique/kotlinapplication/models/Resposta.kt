package com.example.henrique.kotlinapplication.models

class Resposta(
        var firstValue: Int,
        var secondValue: Int,
        var resultAccount: Int,
        var isOperation: String,
        var isCorrect: Boolean){
    override fun toString(): String {
        return "Resposta(firstValue=$firstValue, secondValue=$secondValue, resultAccount=$resultAccount, isOperation='$isOperation', isCorrect=$isCorrect)"
    }
}
