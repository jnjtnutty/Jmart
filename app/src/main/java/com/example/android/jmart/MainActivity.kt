package com.example.android.jmart

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.android.jmart.databinding.JmartMainBinding
import com.example.android.jmart.product.ProductFragment
import com.example.android.jmart.promotion.PromotionFragment
import com.example.android.jmart.video.VideoFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: JmartMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        binding = JmartMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val productFragment = ProductFragment()
        val promotionFragment = PromotionFragment()
        val videoFragment = VideoFragment()

        makeCurrentFragment(productFragment)
        binding.bottomNavigation.setOnNavigationItemSelectedListener { it:MenuItem->
            when(it.itemId){
                R.id.ic_product -> makeCurrentFragment(productFragment)
                R.id.ic_promotion -> makeCurrentFragment(promotionFragment)
                R.id.ic_video -> makeCurrentFragment(videoFragment)
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