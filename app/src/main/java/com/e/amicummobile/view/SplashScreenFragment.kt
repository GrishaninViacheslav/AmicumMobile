package com.e.amicummobile.view

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.amicummobile.R

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

        val imgLogoSplash = binding.imgLogoSplash
        val tvTopTextView = binding.tvTopTextView
        val tvBottomTextView = binding.tvBottomTextView

        val alphaStart = 0.3f
        val alphaEnd = 1f
        val durationAnimation = 1500L

        val spannable = SpannableString(view.context.getString(R.string.splash_bottom_text))
        spannable.setSpan(
            ForegroundColorSpan(Color.BLACK),
            0, 4,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvBottomTextView.text = spannable

        createAnimation(imgLogoSplash, alphaStart, alphaEnd, durationAnimation)                     // центральный лого
        createAnimation(tvTopTextView, alphaStart, alphaEnd, durationAnimation)                     // верхняя строчка
        createAnimation(tvBottomTextView, alphaStart, alphaEnd, durationAnimation)                  // нижняя строчка


        Handler(Looper.getMainLooper()).postDelayed({
            parentFragmentManager.beginTransaction().remove(this).commitNow()
        }, 3000)
    }

    /**
     * Метод установки анимации на элементы заставки
     */
    fun createAnimation(element: View, alphaStart: Float, alphaEnd: Float, durationAnimation: Long) {
        element.setAlpha(alphaStart)
        element.animate()
            .alpha(alphaEnd)
            .setDuration(durationAnimation)
            .setListener(null)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SplashScreenFragment()
    }
}