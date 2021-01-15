package com.example.android.jmart.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.jmart.database.ProductData

class ListAdapter(private val list: List<ProductData>)
    : RecyclerView.Adapter<MobileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MobileViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MobileViewHolder, position: Int) {
        val mobile: ProductData = list[position]
        holder.bind(mobile)
    }

    override fun getItemCount(): Int = list.size

}