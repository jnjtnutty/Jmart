package com.example.android.jmart.promotion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.jmart.databinding.FragmentPromotionBinding

class PromotionFragment : Fragment() {
    private lateinit var binding: FragmentPromotionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPromotionBinding.inflate(layoutInflater)
        return binding.root
    }
}