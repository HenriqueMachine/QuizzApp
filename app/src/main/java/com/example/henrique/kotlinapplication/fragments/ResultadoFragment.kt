package com.example.henrique.kotlinapplication.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.henrique.kotlinapplication.R
import com.example.henrique.kotlinapplication.adapters.RecyclerAdapterResposta
import com.example.henrique.kotlinapplication.models.Resposta
import kotlinx.android.synthetic.main.fragment_resultado.*

class ResultadoFragment : Fragment() {

    private var adapter: RecyclerAdapterResposta? = null

    private var listRespostas : ArrayList<Resposta>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_resultado, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listRespostas = arguments?.getParcelableArrayList<Resposta>("RESPOSTAS")

        initViews()

    }

    private fun initViews() {

        adapter = RecyclerAdapterResposta(ArrayList(),activity!!)

        recyclerview_resultado.layoutManager = LinearLayoutManager(activity)

        recyclerview_resultado.adapter = adapter


        if (listRespostas != null) {
            adapter?.replaceData(listRespostas!!)
        }
    }


}
