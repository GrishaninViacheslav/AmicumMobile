package com.e.amicummobile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.amicummobile.R
import com.e.amicummobile.databinding.MainMenuFragmentBinding
import com.e.amicummobile.viewmodel.StoreAmicum

/**
 * Главное меню системы АМИКУМ
 */
class MainMenuFragment : Fragment(), IAppBarTopMain {

    private var _binding: MainMenuFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var storeAmicum: StoreAmicum

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainMenuFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storeAmicum = ViewModelProvider(this).get(StoreAmicum::class.java)
        binding.openMainMenu.setOnClickListener {                                                                // обработка нажания кнопки закрыть
            binding.drawerMainMenu.openDrawer(GravityCompat.START)
        }
        initFragment()                                                                              // инициализируем фрагмент

    }

    /**
     * Метод инициализации фрагмента
     */
    private fun initFragment() {
        childFragmentManager.beginTransaction()                                                    // загружаем AppBarTop                                                                 // поверх открываем всплывающее окно, которое закроется через 5 секунд
            .add(R.id.containerAppBar, AppBarTopMainFragment.newInstance())
            .commitNow()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainMenuFragment()
    }

    /**
     * открытие главного меню
     */
    override fun openSettings(string: String) {
        binding.drawerMainMenu.openDrawer(GravityCompat.START)
    }

    override fun openNotifications(string: String) {
        TODO("Not yet implemented")
    }


}