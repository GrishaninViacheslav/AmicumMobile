package com.e.amicummobile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.amicummobile.R
import com.e.amicummobile.databinding.AuthorizationFragmentBinding
import com.e.amicummobile.viewmodel.StoreAmicum

/**
 * Страница авторизации пользователя в системе
 */
class AuthorizationFragment : Fragment() {

    private var _binding: AuthorizationFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var storeAmicum: StoreAmicum                                                                       // центральное хранилище приложения


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AuthorizationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storeAmicum =
            ViewModelProvider(this).get(StoreAmicum::class.java)                                                  // подключаем центрально хранилище
        binding.btnCloseApplication.setOnClickListener {                                                                // обработка нажания кнопки закрыть
            closeApp()
        }
        binding.btnLogin.setOnClickListener {                                                                           // обработчик кнопки авторизации
            var statusCheckField: Boolean = true
            binding.lblMessage.text = ""

            if (binding.txtPwd.text?.isEmpty() == true) {
                statusCheckField = false
                binding.lblMessage.text = getString(R.string.emptyPwd)
                binding.txtPwd.setBackgroundResource(R.drawable.field_rounded_red)
            } else {
                binding.txtPwd.setBackgroundResource(R.drawable.field_rounded)
            }

            if (binding.txtLogin.text.isEmpty()) {
                statusCheckField = false
                binding.lblMessage.text = getString(R.string.emptyLogin)
                binding.txtLogin.setBackgroundResource(R.drawable.field_rounded_red)
            } else {
                binding.txtLogin.setBackgroundResource(R.drawable.field_rounded)
            }

            if (statusCheckField) {
                storeAmicum.getLogin(
                    binding.txtLogin.text.toString(),
                    binding.txtPwd.text.toString(),
                    binding.checkBox.isChecked
                )
//                binding.lblMessage.text = getString(R.string.wrongLoginPwd)
//                binding.txtLogin.setBackgroundResource(R.drawable.field_rounded_red)
//                binding.txtPwd.setBackgroundResource(R.drawable.field_rounded_red)
            }
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