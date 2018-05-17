package com.example.henrique.kotlinapplication.models

import android.os.Parcel
import android.os.Parcelable

class Resposta(
        var firstValue: Int,
        var secondValue: Int,
        var resultAccount: Int,
        var isOperation: String,
        var isCorrect: Boolean) : Parcelable {
    override fun toString(): String {
        return "Resposta(firstValue=$firstValue, secondValue=$secondValue, resultAccount=$resultAccount, isOperation='$isOperation', isCorrect=$isCorrect)"
    }

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(firstValue)
        writeInt(secondValue)
        writeInt(resultAccount)
        writeString(isOperation)
        writeInt((if (isCorrect) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Resposta> = object : Parcelable.Creator<Resposta> {
            override fun createFromParcel(source: Parcel): Resposta = Resposta(source)
            override fun newArray(size: Int): Array<Resposta?> = arrayOfNulls(size)
        }
    }
}
