package com.bipapp.apps.exoplayeronline_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.bipapp.apps.exoplayeronline_.databinding.ActivityVideoPlayerBinding

class VideoPlayerActivity : AppCompatActivity() {

    lateinit var binding: ActivityVideoPlayerBinding
    lateinit var player: ExoPlayer
    private var isPlayerReady = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val videoUrl = intent.getStringExtra("videoUrl")


//initialize player
        player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player

        //set media item to player
        val mediaItem = MediaItem.fromUri(videoUrl!!)
        player.setMediaItem(mediaItem)

        //prepare player
        player.prepare()
        player.play()

        //incase of any error occurs while playing the video
        player.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                if (isPlaying) {
                    // Video is ready and playing, hide loading view and show player view
                    isPlayerReady = true
                    binding.loadingImage.visibility = View.GONE
                    binding.playerView.visibility = View.VISIBLE
                }
            }

            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                Toast.makeText(applicationContext, "Error occurred", Toast.LENGTH_SHORT).show()
            }
        })


    }

    override fun onStart() {
        super.onStart()

        player.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        player.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}