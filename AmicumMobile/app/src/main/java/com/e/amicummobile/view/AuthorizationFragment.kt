package com.e.amicummobile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.amicummobile.databinding.AuthorizationFragmentBinding

/**
 * Страница авторизации пользователя в системе
 */
class AuthorizationFragment : Fragment() {

    private var _binding: AuthorizationFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AuthorizationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.btnCloseApplication?.setOnClickListener {                                                             // обработка нажания кнопки закрыть
            closeApp()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = AuthorizationFragment()
    }

    /**
     * Метод закрытия приложения
     */
    private fun closeApp() {
        activity?.finishAffinity()
    }
}