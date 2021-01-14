package com.example.android.jmart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.jmart.databinding.FragmentLoginBinding
import com.example.android.jmart.network.GetMobile
import com.example.android.jmart.network.GetPromotion
import com.example.android.jmart.network.UserLogin

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater)

        binding.loginButton.setOnClickListener {
            UserLogin(requireContext()).userLogin()
            GetPromotion(requireContext()).getPro()
            GetMobile(requireContext()).getMobile()
        }

        return binding.root
    }
}