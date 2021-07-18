package com.e.amicummobile.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.e.amicummobile.R
import com.e.amicummobile.databinding.HeaderNavigationMainDrawerBinding
import com.e.amicummobile.databinding.NavigationMainMenuFragmentBinding
import com.e.amicummobile.viewmodel.StoreAmicum

/**
 * Главное навигационное меню - справа
 */
class NavigationMainMenuFragment : Fragment() {

    private var _binding: HeaderNavigationMainDrawerBinding? = null
    private val binding get() = _binding!!

    private lateinit var storeAmicum: StoreAmicum

    private var mCallback: IAppMainMenu? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HeaderNavigationMainDrawerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storeAmicum = ViewModelProvider(this).get(StoreAmicum::class.java)

        binding.btnMainMenuOnMain.setOnClickListener {                                              // обработка нажания кнопки перейти на главный фрагмент
            mCallback!!.openFragment("MainFragment")
        }

        binding.btnMainMenuNotifications.setOnClickListener {                                       // обработка нажания кнопки открыть уведомления
            mCallback!!.openFragment("NotificationFragment")
        }

    }

    companion object {
        fun newInstance() = NavigationMainMenuFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCallback = context as IAppMainMenu
    }
}