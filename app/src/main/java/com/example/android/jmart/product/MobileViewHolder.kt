package com.example.android.jmart.product

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.jmart.R
import com.example.android.jmart.database.ProductData
import java.net.URL


class MobileViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_model, parent, false)) {

    private var mImageView: ImageView? = null
    private var mMobileNameView: TextView? = null

    init {
        mImageView = itemView.findViewById(R.id.imgIphone)
        mMobileNameView = itemView.findViewById(R.id.nameModel)
    }

    fun bind(iphone: ProductData) {
        mMobileNameView?.text = iphone.display
//        val url = URL(iphone.imageNormal)
//        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//        mImageView?.setImageBitmap(bmp)
    }
}
