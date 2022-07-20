package com.example.animconer.views.activities

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.animconer.BuildConfig
import com.example.animconer.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class YoutubeActivity : YouTubeBaseActivity() {

    val api_key = BuildConfig.YOUTUBE_API_KEY
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)

        val ytPlayer = findViewById<YouTubePlayerView>(R.id.ytPlayer)
        val animeName = findViewById<TextView>(R.id.tvName)

        val animationName = intent.getStringExtra("EXTRA_ANIME_NAME")
        val animationVideoId = intent.getStringExtra("EXTRA_YOUTUBE_VIDEO_ID")

        animeName.text = animationName
        ytPlayer.initialize(api_key,object:YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                p2: Boolean
            ) {
                player?.loadVideo(animationVideoId)
                player?.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(this@YoutubeActivity, "Video Player Error", Toast.LENGTH_SHORT).show()
            }

        })
    }
}
