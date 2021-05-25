package com.e.amicummobile

import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.view.View
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import com.e.amicummobile.view.AutorizationFragment
import com.e.amicummobile.view.MainMenuFragment
import com.e.amicummobile.view.SplashScreenFragment
import com.e.amicummobile.viewmodel.StoreAmicum

/**
 * Главная активити системы - старт отсюда
 */
class MainActivity : AppCompatActivity() {
    private lateinit var storeAmicum: StoreAmicum

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storeAmicum = ViewModelProvider(this).get(StoreAmicum::class.java)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()                                                                   // инициализируем главное меню
                .replace(R.id.container, MainMenuFragment.newInstance())
                .commitNow()

            if (!storeAmicum.checkUserSession()) {
                supportFragmentManager.beginTransaction()                                                               // сразу заполняем фреймом авторизации
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .add(R.id.container, AutorizationFragment.newInstance())
                    .commitNow()
            }

            supportFragmentManager.beginTransaction()                                                                   // поверх открываем всплывающее окно, которое закроется через 5 секунд
                .add(R.id.container, SplashScreenFragment.newInstance())
                .commitNow()
        }
    }
}