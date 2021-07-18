package com.e.amicummobile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.amicummobile.R
import com.e.amicummobile.config.Const
import com.e.amicummobile.databinding.NotificationFragmentBinding
import com.e.amicummobile.viewmodel.StoreAmicum

/**
 * Уведомления
 */
class NotificationFragment : Fragment() {

    private var _binding: NotificationFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var storeAmicum: StoreAmicum

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NotificationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storeAmicum = ViewModelProvider(this).get(StoreAmicum::class.java)

        initFragment()                                                                              // инициализируем фрагмент

    }

    /**
     * Метод инициализации фрагмента
     */
    private fun initFragment() {
        childFragmentManager.beginTransaction()                                                    // загружаем AppBarTop                                                                 // поверх открываем всплывающее окно, которое закроется через 5 секунд
            .add(
                R.id.containerAppBar, AppBarTopMainFragment.newInstance(
                    "Уведомления",
                    Const.APP_BAR_ONLY_BACK,
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
        fun newInstance() = NotificationFragment()
    }


}