package com.example.android.jmart.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android.jmart.R
import com.example.android.jmart.database.ProductDatabase
import com.example.android.jmart.databinding.FragmentProductBinding

class ProductFragment : Fragment() {
//    private lateinit var binding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


//        binding = FragmentProductBinding.inflate(layoutInflater)
//        Log.i("Product","Product ViewModelProvider")
//        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        val binding: FragmentProductBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product, container, false)
        val application = requireNotNull(this.activity).application

        val dataSource = ProductDatabase.getInstance(application)?.productDatabaseDao

        val viewModelFactory = ProductViewModelFactory(dataSource!!, application)

        val productViewModel =
                ViewModelProvider(
                        this, viewModelFactory).get(ProductViewModel::class.java)

        binding.lifecycleOwner = this
        binding.productViewModel = productViewModel

        return binding.root
    }
}

