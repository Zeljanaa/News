package com.example.newsapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivitySplashScreenBinding
import com.example.newsapp.utils.animate

class SplashScreen : AppCompatActivity() {


    lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        /**
         * Animate splash image and splash text
         * using startAnimation method from util/animations package
         * */
        binding.apply {
            imageSplash.startAnimation(animate(this@SplashScreen, R.anim.splash_animation))
            splashText.startAnimation(animate(this@SplashScreen, R.anim.splash_animation))
            jikuze.startAnimation(animate(this@SplashScreen, R.anim.splash_animation))
        }

        /**
         * Navigate to Login Activity
         * */
        val toLogin = Intent(this, Login::class.java)
        //running on thread
        val timer = object : Thread() {
            override fun run() {
                try {
                    sleep(4000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    startActivity(toLogin)
                    finish()
                }
            }
        }
        timer.start()

    }
}