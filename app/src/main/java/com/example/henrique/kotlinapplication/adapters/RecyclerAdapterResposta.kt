package com.example.henrique.kotlinapplication.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.henrique.kotlinapplication.R
import com.example.henrique.kotlinapplication.models.Resposta
import kotlinx.android.synthetic.main.item_set_results.view.*

class RecyclerAdapterResposta(var mlista: ArrayList<Resposta>,val context: Context) : RecyclerView.Adapter<RecyclerAdapterResposta.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.item_set_results, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val resposta = mlista[position]

        if (resposta.isCorrect) {
            holder.itemView.tvValor1.text = resposta.firstValue.toString()
            holder.itemView.tvValor2.text = resposta.secondValue.toString()
            holder.itemView.tvOperacao.text = resposta.isOperation
            holder.itemView.tvResultado.text = resposta.resultAccount.toString()
            holder.itemView.tvResultado.setBackgroundColor(R.color.colorNotError!!)
        } else {
            holder.itemView.tvValor1.text = resposta.firstValue.toString()
            holder.itemView.tvValor2.text = resposta.secondValue.toString()
            holder.itemView.tvOperacao.text = resposta.isOperation
            holder.itemView.tvResultado.text = resposta.resultAccount.toString()
            holder.itemView.tvResultado.setBackgroundColor(R.color.colorError!!)}
    }


    fun replaceData(respostas:ArrayList<Resposta>){

        mlista = respostas
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return mlista.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

}


