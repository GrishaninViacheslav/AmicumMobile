package com.e.amicummobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import com.e.amicummobile.databinding.ActivityMainBinding
import com.e.amicummobile.interfaces.IAppBarTopMain
import com.e.amicummobile.interfaces.IAppMain
import com.e.amicummobile.interfaces.IAppMainMenu
import com.e.amicummobile.view.*
import com.e.amicummobile.viewmodel.StoreAmicum
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Главная активити системы - старт отсюда
 */
class MainActivity : AppCompatActivity(), IAppBarTopMain, IAppMainMenu, IAppMain {
    private lateinit var storeAmicum: StoreAmicum

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initViewModel()

        if (savedInstanceState == null) {
            if (!storeAmicum.checkUserSession()) {
                supportFragmentManager.beginTransaction()                                           // сразу заполняем фреймом авторизации
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .add(R.id.container, AuthorizationFragment.newInstance())
                    .commitNow()
            }

            supportFragmentManager.beginTransaction()                                               // поверх открываем всплывающее окно, которое закроется через 5 секунд
                .add(R.id.container, SplashScreenFragment.newInstance())
                .commitNow()
        }
    }

    private fun initViewModel() {
        val viewModel: StoreAmicum by viewModel()
        storeAmicum = viewModel
    }

    /**
     * Инициализация приложения при успешной авториазции
     */
    override fun initApp(string: String) {
        supportFragmentManager.beginTransaction()                                                   // инициализируем правое главное меню
            .add(R.id.navigationMainMenuContainer, NavigationMainMenuFragment.newInstance())
            .commitNow()
    }


    override fun openMainMenu(string: String) {
        binding.drawerMainMenu.openDrawer(GravityCompat.START)
    }

    /**
     * Центральный обработкчик открытия пунктов меню
     */
    override fun openFragment(nameFragment: String) {
        if (binding.drawerMainMenu.isDrawerOpen(GravityCompat.START)) {
            binding.drawerMainMenu.closeDrawer(GravityCompat.START)
        }
        when (nameFragment) {
            "NotificationFragment" -> openNotifications("NotificationFragment")
            "MainFragment" -> openMain("MainFragment")
        }
    }

    /**
     * Метод открытия фрагмента уведомлений
     */
    override fun openNotifications(string: String) {
        binding.container.findNavController().navigate(R.id.notificationFragment)
    }

    /**
     * Метод открытия главной страницы
     */
    override fun openMain(nameFragment: String) {
        binding.container.findNavController().navigate(R.id.mainFragment)
    }

    override fun backFragment(nameFragment: String) {
        binding.container.findNavController().popBackStack()
    }


}