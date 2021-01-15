package com.example.android.jmart

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.android.jmart.database.ProductDataDAO
import com.example.android.jmart.database.ProductDatabase
import com.example.android.jmart.databinding.JmartMainBinding
import com.example.android.jmart.network.SessionManager
import com.example.android.jmart.product.ProductFragment
import com.example.android.jmart.promotion.PromotionFragment
import com.example.android.jmart.video.VideoFragment


class MainActivity: AppCompatActivity() {

    private lateinit var binding: JmartMainBinding
    lateinit var sessionManager: SessionManager

    private lateinit var dataSource: ProductDataDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT

        dataSource = ProductDatabase.getInstance(application).productDatabaseDao

        sessionManager = SessionManager(this)

        binding = JmartMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val productFragment = ProductFragment(dataSource)
        val promotionFragment = PromotionFragment(dataSource)
        val videoFragment = VideoFragment()
        val loginFragment = LoginFragment()

        makeCurrentFragment(productFragment)
        binding.bottomNavigation.setOnNavigationItemSelectedListener { it:MenuItem->
            when(it.itemId){
                R.id.ic_product -> makeCurrentFragment(productFragment)
                R.id.ic_promotion -> makeCurrentFragment(promotionFragment)
                R.id.ic_video -> makeCurrentFragment(videoFragment)
                R.id.ic_login -> makeCurrentFragment(loginFragment)
            }
            true
        }
     }

    private fun makeCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
    }
}