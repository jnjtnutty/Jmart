package com.example.android.jmart.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.jmart.R
import com.example.android.jmart.database.ProductDatabase
import com.example.android.jmart.databinding.FragmentProductBinding
import com.example.android.jmart.network.Api


class ProductFragment : Fragment() {
    private val ListMobile = listOf(
        Api.MobileSubResponse(
            "IPHONE SE 2",
            "https://jmb-master.jventures.co.th/jmb-extend/v1/products/JAY_MOBILE_CATEGORY_SET/09/iPhoneSE_2_128/iPhoneSE2_878063505.jpg"
        ),
        Api.MobileSubResponse(
            "iPhone 11 Pro",
            "https://fibo.jaymart.org/asset/Images/02/Mobilesub/dd8c2b6b-4b4c-4838-a24f-6217925c2d59.png"
        ),
        Api.MobileSubResponse(
            "iPhone 11 Pro Max",
            "https://fibo.jaymart.org/asset/Images/02/Mobilesub/a17e9165-c7fe-4951-a977-cba60b399f4b.png"
        ),
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentProductBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = ProductDatabase.getInstance(application)?.productDatabaseDao

        val viewModelFactory = ProductViewModelFactory(dataSource!!, application)

        val productViewModel =
                ViewModelProvider(
                    this, viewModelFactory
                ).get(ProductViewModel::class.java)

        binding.lifecycleOwner = this
        binding.productViewModel = productViewModel

        binding.recycleView.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(ListMobile)
        }

        return binding.root
    }

}

