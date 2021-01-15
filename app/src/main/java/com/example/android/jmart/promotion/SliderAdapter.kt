package com.example.android.jmart.promotion

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.android.jmart.R
import com.example.android.jmart.database.PromotionPicData

class SliderAdapter: PagerAdapter {

    private var context: Context
    private var images: List<PromotionPicData>
    lateinit var inflater: LayoutInflater

    constructor(context: Context,images:List<PromotionPicData>):super(){
        this.context = context
        this.images = images
    }
    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object` as RelativeLayout


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var image: ImageView

        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view: View = inflater.inflate(R.layout.slide_image_item,container,false)
        image = view.findViewById(R.id.sliderImage)
        var url_test = "https://jmb-master.jventures.co.th/jmb-extend/v1/products/JAY_MOBILE_CATEGORY_SET/09/iPhoneSE_2_128/iPhoneSE2_878063505.jpg"
        image?.let {
            Glide.with(it)
                .load(url_test)
                .override(256, 144)
                .into(image)
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}