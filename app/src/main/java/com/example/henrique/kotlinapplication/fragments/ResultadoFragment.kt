package com.example.henrique.kotlinapplication.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.henrique.kotlinapplication.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ResultadoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_resultado, container, false)
        
        //MOCK List
        var arrayList = mutableListOf<Historic>()
        arrayList.add(Historic("R$300,00", "Tranferência recebida", "02 maio 2018", "depósito"))
        arrayList.add(Historic("R$300,00", "Tranferência recebida", "02 maio 2018", "depósito"))
        arrayList.add(Historic("R$300,00", "Tranferência recebida", "02 maio 2018", "depósito"))
        arrayList.add(Historic("R$300,00", "Tranferência recebida", "02 maio 2018", "depósito"))
        arrayList.add(Historic("R$300,00", "Tranferência recebida", "02 maio 2018", "depósito"))

        mLayoutManager = LinearLayoutManager(activity)
        adapteHistoric = MyHistoricAdapter(arrayList)
        rcHistoric = view.findViewById<RecyclerView>(R.id.rvHistoricBottomSheet).apply {
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = mLayoutManager
            // specify an viewAdapter (see also next example)
            adapter = adapteHistoric
        }
        
        return view
    }


}
