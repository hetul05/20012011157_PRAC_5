package com.example.mp3_practical_5

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class MusicService:Service() {
    lateinit var mediaPlayer:MediaPlayer
    private val TAG="MusicService"
    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(!this::mediaPlayer.isInitialized){
            Log.i(TAG,"buttonplayonclick")
            mediaPlayer= MediaPlayer.create(this, R.raw.song)
        }
        if(intent!=null){
            val data:String? =intent.getStringExtra("service")
            if(data=="play & pause"){
                if(!mediaPlayer.isPlaying){
                    mediaPlayer.start()
                }
                else{
                    mediaPlayer.pause()
                }
            }
        }
        else{
            mediaPlayer.start()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        mediaPlayer.stop()
        super.onDestroy()
    }
}
