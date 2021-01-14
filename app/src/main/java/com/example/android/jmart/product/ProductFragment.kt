package com.example.android.jmart.product


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.jmart.R
import com.example.android.jmart.database.ProductDatabase
import com.example.android.jmart.databinding.FragmentProductBinding
import com.example.android.jmart.network.SessionManager


class ProductFragment : Fragment() {

//    private val sessionManager: SessionManager = SessionManager(context)

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



//        binding.recycleView.apply{
//            layoutManager = LinearLayoutManager(activity)
//            adapter = ListAdapter(ListMobile)
//        }

        return binding.root
    }

}

