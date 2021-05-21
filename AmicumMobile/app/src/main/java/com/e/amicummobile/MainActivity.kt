package com.e.amicummobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.amicummobile.view.AutorizationFragment
import com.e.amicummobile.view.SplashScreenFragment

/**
 * Главная активити системы - старт отсюда
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AutorizationFragment.newInstance())
                .commitNow()

            supportFragmentManager.beginTransaction()
                .add(R.id.container, SplashScreenFragment.newInstance())
                .commitNow()
        }
    }
}