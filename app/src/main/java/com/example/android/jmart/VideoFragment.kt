package com.example.android.jmart

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import com.example.android.jmart.databinding.FragmentVideoBinding
import kotlinx.android.synthetic.main.fragment_video.*

class VideoFragment : Fragment() {
    private lateinit var binding: FragmentVideoBinding
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentVideoBinding.inflate(layoutInflater)

        var videoPath="android.resource://com.example.android.jmart/"+R.raw.advertise
        var url= Uri.parse(videoPath)

        val mediaController = MediaController(activity)
        mediaController.setAnchorView(binding.videoView)

        val videoView = binding.videoView
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(url)
        videoView.requestFocus()
        videoView.start()

        return binding.root
    }
}