package com.example.android.jmart.promotion

//import SessionManager

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.example.android.jmart.R
import com.example.android.jmart.database.ProductDataDAO
import com.example.android.jmart.database.PromotionPicData
import com.example.android.jmart.databinding.FragmentPromotionBinding

class PromotionFragment(dataSource: ProductDataDAO) : Fragment() {

    lateinit var application: Application

    var _dataSource = dataSource

    private lateinit var promotionViewModel: PromotionViewModel

    private lateinit var binding: FragmentPromotionBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        application = requireNotNull(this.activity).application

        promotionViewModel = PromotionViewModel(_dataSource, application)

        val images: List<PromotionPicData> = _dataSource.getAllPro()

        val adapter: PagerAdapter = SliderAdapter(application,images)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_promotion, container, false
        )

        promotionViewModel.getPromotion().observeForever {
            Log.i("print", "proimage : $it")
            promotionViewModel.savePromotionToDatabase(it)
        }

        binding.viewpager.adapter = adapter

        return binding.root
    }

}