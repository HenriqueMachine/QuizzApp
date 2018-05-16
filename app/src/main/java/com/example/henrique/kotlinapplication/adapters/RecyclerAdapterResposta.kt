package com.example.henrique.kotlinapplication.adapters

class RecyclerAdapterResposta(val list: MutableList<Historic>) : RecyclerView.Adapter<MyHistoricAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.line_item_historic, parent, false) as View

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var historic : Historic
        if (list != null){
            historic = list.get(position)

            holder.tvValue.text = historic.valueMoney
            holder.tvDescription.text = historic.description
            holder.tvDate.text = historic.date
            holder.tvType.text = historic.type
        }

    }


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        var tvValue : TextView = view.findViewById(R.id.tvValue)
        var tvDescription : TextView = view.findViewById(R.id.tvDescription)
        var tvDate : TextView = view.findViewById(R.id.tvDate)
        var tvType : TextView = view.findViewById(R.id.tvType)

    }


}


