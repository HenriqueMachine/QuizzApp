package com.example.henrique.kotlinapplication.models

import android.os.Parcel
import android.os.Parcelable

class RespostaEqSimple(
        var firstValue: Int,
        var secondValue: Int,
        var resultAccount: Int,
        var isOperation: String,
        var isCorrect: Boolean,
        var viewType: Int = 2) : Parcelable {
    override fun toString(): String {
        return "Resposta(firstValue=$firstValue, secondValue=$secondValue, resultAccount=$resultAccount, isOperation='$isOperation', isCorrect=$isCorrect)"
    }

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            1 == source.readInt(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(firstValue)
        writeInt(secondValue)
        writeInt(resultAccount)
        writeString(isOperation)
        writeInt((if (isCorrect) 1 else 0))
        writeInt(viewType)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RespostaEqSimple> = object : Parcelable.Creator<RespostaEqSimple> {
            override fun createFromParcel(source: Parcel): RespostaEqSimple = RespostaEqSimple(source)
            override fun newArray(size: Int): Array<RespostaEqSimple?> = arrayOfNulls(size)
        }
    }
}
