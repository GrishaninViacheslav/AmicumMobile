package com.e.amicummobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.e.amicummobile.view.AuthorizationFragment
import com.e.amicummobile.view.IAppBarTopMain
import com.e.amicummobile.view.MainMenuFragment
import com.e.amicummobile.view.SplashScreenFragment
import com.e.amicummobile.viewmodel.StoreAmicum

/**
 * Главная активити системы - старт отсюда
 */
class MainActivity : AppCompatActivity(), IAppBarTopMain {
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
                    .add(R.id.container, AuthorizationFragment.newInstance())
                    .commitNow()
            }

            supportFragmentManager.beginTransaction()                                                                   // поверх открываем всплывающее окно, которое закроется через 5 секунд
                .add(R.id.container, SplashScreenFragment.newInstance())
                .commitNow()
        }
    }

    override fun openMainMenu(string: String) {

    }

    override fun openNotifications(string: String) {

    }


}