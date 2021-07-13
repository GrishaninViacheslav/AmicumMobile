package com.e.amicummobile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

        binding.txtLogin.addTextChangedListener {                                                                       // сброс вывода ошибки при начале ввода текста в поле логина
            binding.layoutLogin.error = null
        }

        binding.txtPwd.addTextChangedListener {                                                                         // сброс вывода ошибки при начале ввода текста в поле пароля
            binding.layoutPwd.error = null
        }

        binding.btnLogin.setOnClickListener {                                                                           // обработчик кнопки авторизации
            var statusCheckField =
                true                                                                                 // статус проверки полей авторизации
            val statusAuthorization: Boolean                                                                            // статус авторизации

            // делаем проверку на пустое поле пароля, если оно пустое, то красим выводим ошибку
            if (binding.txtPwd.text?.isEmpty() == true) {
                statusCheckField = false
                binding.layoutPwd.error = "Пароль не может быть пустым!"
            } else {
                binding.layoutPwd.error = null
            }

            // делаем проверку на пустое поле логина, если оно пустое, то красим выводим ошибку
            if (binding.txtLogin.text?.isEmpty() == true) {
                statusCheckField = false
                binding.layoutLogin.error = "Логин не может быть пустым!"
            } else {
                binding.layoutLogin.error = null
            }

            // выполняем авторизацию
            if (statusCheckField) {
                statusAuthorization = storeAmicum.getLogin(
                    binding.txtLogin.text.toString(),
                    binding.txtPwd.text.toString(),
                    binding.checkBox.isChecked
                )

                if (!statusAuthorization) {
                    binding.layoutLogin.error = "Логин неверный!"
                    binding.layoutPwd.error = "Пароль неверный!"
                } else {
                    binding.layoutLogin.error = null
                    binding.layoutPwd.error = null
                    parentFragmentManager.beginTransaction().remove(this).commitNow()
                }

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