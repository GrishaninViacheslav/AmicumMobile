package com.e.amicummobile.view.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.amicummobile.R
import com.e.amicummobile.config.Const
import com.e.amicummobile.databinding.MainMenuFragmentBinding
import com.e.amicummobile.viewmodel.StoreAmicum
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Главное меню системы АМИКУМ
 */
class MainMenuFragment : Fragment() {

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
        val viewModel: StoreAmicum by viewModel()
        storeAmicum = viewModel
        initFragment()                                                                              // инициализируем фрагмент

    }

    /**
     * Метод инициализации фрагмента
     */
    private fun initFragment() {
        childFragmentManager.beginTransaction()                                                    // загружаем AppBarTop                                                                 // поверх открываем всплывающее окно, которое закроется через 5 секунд
            .add(
                R.id.containerAppBar, AppBarTopMainFragment.newInstance(
                    "Amicum_mobile",
                    Const.APP_BAR_MAIN,
                    "",
                    ""
                )
            )
            .commitNow()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainMenuFragment()
    }
}