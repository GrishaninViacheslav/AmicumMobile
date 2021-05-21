package com.e.amicummobile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.amicummobile.databinding.AutorizationFragmentBinding
import com.e.amicummobile.viewmodel.StoreAmicum

/**
 * Страница авторизации пользователя в системе
 */
class AutorizationFragment : Fragment() {

    private var _binding: AutorizationFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var storeAmicum: StoreAmicum

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AutorizationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        storeAmicum = ViewModelProvider(this).get(StoreAmicum::class.java)
        storeAmicum.getDataFromRemoteSource()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = AutorizationFragment()
    }
}