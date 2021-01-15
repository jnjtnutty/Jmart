package com.example.android.jmart.product


import android.app.Application
import android.content.Context
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.jmart.R
import com.example.android.jmart.database.ProductDataDAO
import com.example.android.jmart.database.ProductDataManager
import com.example.android.jmart.database.ProductDatabase
import com.example.android.jmart.databinding.FragmentProductBinding



class ProductFragment : Fragment() {

    lateinit var application: Application

    private lateinit var dataSource: ProductDataDAO

    private lateinit var productViewModel: ProductViewModel

    lateinit var binding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        application = requireNotNull(this.activity).application

        dataSource = ProductDatabase.getInstance(application)?.productDatabaseDao

        productViewModel = ProductViewModel(dataSource!!, application)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product, container, false
        )

        binding.lifecycleOwner = this
        binding.productViewModel = productViewModel

        binding.clearData.setOnClickListener {
            productViewModel.onClear()
            productViewModel.OnStopProduct()
        }

        productViewModel.getIphone().observeForever {
            Log.i("print", "iphone api : $it")
            productViewModel.saveDataToProductDatabase(it)
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val productAll = dataSource.getAllProduct()
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
            adapter = ListAdapter(productAll)
        }

    }
}

