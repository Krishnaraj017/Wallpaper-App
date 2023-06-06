package com.krish.wallpaper

import androidx.appcompat.app.AppCompatActivity
import android.graphics.drawable.Drawable
import android.app.WallpaperManager
import android.os.Bundle
import com.krish.wallpaper.R
import android.widget.Toast
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.Button
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {
    var wallpaperChange: Button? = null
    var mytimer: Timer? = null
    var drawable: Drawable? = null
    var wpm: WallpaperManager? = null
    var prev = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mytimer = Timer()
        wpm = WallpaperManager.getInstance(this)
        wallpaperChange = findViewById<View>(R.id.button1) as Button
        wallpaperChange!!.setOnClickListener { setwallpaper() }
    }

    private fun setwallpaper() {
        Toast.makeText(this, "Setting wallpaper. Please wait...", Toast.LENGTH_LONG).show()
        mytimer!!.schedule(object : TimerTask() {
            override fun run() {
                if (prev == 1) {
                    drawable = resources.getDrawable(R.drawable.i1)
                    prev = 2
                } else if (prev == 2) {
                    drawable = resources.getDrawable(R.drawable.i2)
                    prev = 3
                } else if (prev == 3) {
                    drawable = resources.getDrawable(R.drawable.i3)
                    prev = 1
                }
                val wallpaper = (drawable as BitmapDrawable?)!!.bitmap
                try {
                    wpm!!.setBitmap(wallpaper)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }, 0, 30000)
    }
}