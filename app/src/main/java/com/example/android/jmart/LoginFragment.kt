package com.example.android.jmart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.jmart.data.sub
import com.example.android.jmart.databinding.FragmentLoginBinding
import com.example.android.jmart.network.GetMobile
import com.example.android.jmart.network.GetPromotion
import com.example.android.jmart.network.UserLogin
import com.example.android.jmart.product.ProductFragment
import com.example.android.jmart.product.ProductViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater)

        val application = requireNotNull(this.activity).application

        binding.loginButton.setOnClickListener {
            UserLogin(requireContext()).userLogin()
        }

        return binding.root
    }
}