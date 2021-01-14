//package com.example.android.jmart.product
//
//import com.example.android.jmart.data.MobileSub
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//
//class ListAdapter(private val list: List<MobileSub>)
//    : RecyclerView.Adapter<MobileViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobileViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        return MobileViewHolder(inflater, parent)
//    }
//
//    override fun onBindViewHolder(holder: MobileViewHolder, position: Int) {
//        val mobile: MobileSub = list[position]
//        holder.bind(mobile)
//    }
//
//    override fun getItemCount(): Int = list.size
//
//}