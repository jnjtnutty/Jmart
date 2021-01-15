package com.example.android.jmart.product


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.jmart.R
import com.example.android.jmart.data.sub
import com.example.android.jmart.database.ProductData
import com.example.android.jmart.database.ProductDataDAO
import com.example.android.jmart.database.ProductDatabase
import com.example.android.jmart.databinding.FragmentProductBinding



class ProductFragment : Fragment() {
    private val iphoneList: List<ProductData> = listOf(ProductData(1, "256GB", ";http", "123"),
        ProductData(2, "256GB", ";http", "123"),
        ProductData(3, "256GB", ";http", "123"),
        ProductData(4, "256GB", ";http", "123"),
        ProductData(5, "256GB", ";http", "123"))



    lateinit var dataSource: ProductDataDAO

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

            val binding: FragmentProductBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_product, container, false
            )

            val application = requireNotNull(this.activity).application

            dataSource = ProductDatabase.getInstance(application)?.productDatabaseDao

            val productViewModel = ProductViewModel(dataSource!!, application)

            binding.lifecycleOwner = this
            binding.productViewModel = productViewModel

            val listAdapter = ListAdapter(iphoneList)
            binding.recyclerView.apply {
                layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.HORIZONTAL, false)
                adapter = listAdapter
                binding.clearData.setOnClickListener {
                    productViewModel.onClear()
                }
            }
            productViewModel.getIphone().observeForever {
                Log.i("print", "iphone api : $it")
                var mobiles = it
                productViewModel.saveDataToProductDatabase(mobiles)
            }
            return binding.root
        }

}

