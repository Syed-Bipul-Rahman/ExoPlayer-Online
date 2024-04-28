package com.bipapp.apps.exoplayeronline_

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bipapp.apps.exoplayeronline_.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var videoUrl: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //video link https://apiholder.000webhostapp.com/videos/Zindagi.mp4
        //https://apiholder.000webhostapp.com/videos/lock.mp4


        binding.btnVideo1.setOnClickListener {
            videoUrl = "https://apiholder.000webhostapp.com/videos/Zindagi.mp4"
            goToPlayer(videoUrl)

        }
        binding.btnVideo2.setOnClickListener {
            videoUrl = "https://apiholder.000webhostapp.com/videos/lock.mp4"
            goToPlayer(videoUrl)
        }

    }

    private fun goToPlayer(videoUrl: String) {
        val intent = Intent(this, VideoPlayerActivity::class.java)
        intent.putExtra("videoUrl", videoUrl)
        startActivity(intent)
    }
}