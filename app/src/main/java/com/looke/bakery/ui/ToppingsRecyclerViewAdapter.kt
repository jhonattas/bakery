package com.looke.bakery.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.looke.bakery.R
import com.looke.bakery.model.ProductWithTopping

class ToppingsRecyclerViewAdapter(private val values: List<ProductWithTopping>)
    : RecyclerView.Adapter<ToppingsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_topping, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.idView.text = item.toppingId
        holder.typeView.text = item.toppingContent
        holder.nameView.text = item.originalName
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val nameView: TextView = view.findViewById(R.id.name)
        val typeView: TextView = view.findViewById(R.id.type)
        val ppuView: TextView = view.findViewById(R.id.ppu)

        override fun toString(): String {
            return super.toString() + " '" + typeView.text + "'"
        }
    }
}