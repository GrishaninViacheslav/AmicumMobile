package com.e.amicummobile.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.e.amicummobile.databinding.SplashScreenFragmentBinding

/**
 * Страница авторизации пользователя в системе
 */
class SplashScreenFragment : Fragment() {

    private var _binding: SplashScreenFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SplashScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            parentFragmentManager.beginTransaction().remove(this).commitNow()
        }, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SplashScreenFragment()
    }
}