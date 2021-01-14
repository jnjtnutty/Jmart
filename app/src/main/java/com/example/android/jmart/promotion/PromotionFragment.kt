package com.example.android.jmart.promotion

//import SessionManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.jmart.databinding.FragmentPromotionBinding
import com.example.android.jmart.network.GetPromotion

class PromotionFragment : Fragment() {

    private lateinit var binding: FragmentPromotionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPromotionBinding.inflate(layoutInflater)

        GetPromotion(requireContext()).getPro()

        return binding.root
    }
}