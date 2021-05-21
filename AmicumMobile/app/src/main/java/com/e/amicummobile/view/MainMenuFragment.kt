package com.e.amicummobile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.amicummobile.databinding.MainMenuFragmentBinding
import com.e.amicummobile.viewmodel.StoreAmicum

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storeAmicum = ViewModelProvider(this).get(StoreAmicum::class.java)
        storeAmicum.getDataFromRemoteSource()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainMenuFragment()
    }
}