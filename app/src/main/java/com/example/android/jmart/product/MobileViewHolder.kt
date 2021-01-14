package com.example.android.jmart.product

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.jmart.R


class MobileViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_model, parent, false)) {

    private var mImageView: ImageView? = null
    private var mMobileNameView: TextView? = null

    init {
        mImageView = itemView.findViewById(R.id.mobileImage)
        mMobileNameView = itemView.findViewById(R.id.mobileName)
    }

//    fun bind(mobile: com.example.android.jmart.data.MobileSub) {
//        val url = URL(mobile.imageNormal)
//        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//        mImageView?.setImageBitmap(bmp)
//        mMobileNameView?.text = mobile.imageNormal
//    }
}
