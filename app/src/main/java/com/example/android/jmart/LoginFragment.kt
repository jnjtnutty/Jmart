package com.example.android.jmart

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.jmart.databinding.FragmentLoginBinding
import com.example.android.jmart.network.GetMobile
import com.example.android.jmart.network.UserLogin

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    lateinit var application: Application

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(layoutInflater)

        application = requireNotNull(this.activity).application

        binding.loginButton.setOnClickListener {
            UserLogin(requireContext()).userLogin()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        GetMobile(application).getMobile().observeForever {
            if(it!=null){
                binding.statusText.text = "Login Success"
            }else{
                binding.statusText.text = "Login not Success"
            }
        }
    }
}