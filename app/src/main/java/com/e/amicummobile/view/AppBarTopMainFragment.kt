package com.e.amicummobile.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.amicummobile.databinding.AppBarTopMainFragmentBinding
import com.e.amicummobile.viewmodel.StoreAmicum

/**
 * меню приложения в шапке
 */
class AppBarTopMainFragment : Fragment() {

    private var _binding: AppBarTopMainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var storeAmicum: StoreAmicum

    private var mCallback: IAppBarTopMain? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AppBarTopMainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storeAmicum = ViewModelProvider(this).get(StoreAmicum::class.java)
        binding.btnAppBarMainMenu.setOnClickListener {                                                                // обработка нажания кнопки закрыть
            Log.println(Log.INFO, "AppBarTopMainFragment.onViewCreated", "Нажал настройка")
            if (parentFragment is MainMenuFragment) {
                (parentFragment as MainMenuFragment).openMainMenu("Открыть главную панель")
            }
            mCallback!!.openMainMenu("Открыть главную панель")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = AppBarTopMainFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCallback = context as IAppBarTopMain
    }
}