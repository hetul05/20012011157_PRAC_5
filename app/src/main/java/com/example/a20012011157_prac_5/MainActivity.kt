package com.example.a20012011157_prac_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity() {
    enum class eMusic {
        PLAY,
        PAUSE
    }
    var flag = eMusic.PLAY //song is playing
    private val TAG = "MainActivity"

    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val play_btn = findViewById<FloatingActionButton>(R.id.play)
        play_btn.setOnClickListener {
            Log.i(TAG, "buttonplayonclick")
            Intent(applicationContext, MusicService::class.java).putExtra("service", "play & pause")
                .apply {
                    startService(this)
                }

            if (flag == eMusic.PLAY) {
                setPause()
                flag = eMusic.PAUSE
            } else {
                setPlay()
                flag = eMusic.PLAY
            }
        }
        //Stop-button
        val stop_btn = findViewById<FloatingActionButton>(R.id.stop)
        stop_btn.setOnClickListener {
            Intent(applicationContext, MusicService::class.java).apply {
                stopService(this)
            }
            setPlay()
        }

        //Favorite-button
        val fav_btn = findViewById<FloatingActionButton>(R.id.fav_btn)
        fav_btn.setOnClickListener {
            fav_btn.setColorFilter(ContextCompat.getColor(applicationContext, R.color.pink))
        }
    }

    private fun setPause() {
        val play_btn = findViewById<FloatingActionButton>(R.id.play)
        play_btn.setImageResource(R.drawable.ic_baseline_pause_24)
    }

    private fun setPlay() {
        val play_btn = findViewById<FloatingActionButton>(R.id.play)
        play_btn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
    }
}

}
}