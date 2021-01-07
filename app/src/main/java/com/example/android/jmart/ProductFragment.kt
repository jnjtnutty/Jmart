package com.example.android.jmart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

class ProductFragment : Fragment() {
    private lateinit var viewModel: ProductViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i("ProductFragment","Product Fragment call ProductProvider")
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)


    }
}