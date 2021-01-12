package com.example.android.jmart.product

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.jmart.network.Api

class ListAdapter(private val list: List<Api.MobileSubResponse>)
    : RecyclerView.Adapter<MobileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MobileViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MobileViewHolder, position: Int) {
        val mobile: Api.MobileSubResponse = list[position]
        holder.bind(mobile)
    }

    override fun getItemCount(): Int = list.size

}