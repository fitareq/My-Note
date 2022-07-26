package com.example.mynote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.mynote.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //hide toolbar
        supportActionBar?.hide()

        //start main activity after 2 second
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }, 2000)
    }
}