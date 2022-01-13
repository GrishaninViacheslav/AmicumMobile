package com.e.amicummobile.view.settings

import android.app.UiModeManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.e.amicummobile.R
import com.e.amicummobile.databinding.SettingsFragmentBinding
import com.e.amicummobile.view.menu.AppBarTopMainFragment
import com.e.amicummobile.viewmodel.StoreSettings
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

// TODO: (Вячеслав): AuthorizationFragment и NotificationFragment наследуются от дженерик класса BaseFragment,
//              которому передаётся data class как параметр T. Но я не разобрался как этот параметр используется и
//              как для SettingsFragment может быть полезен BaseFragment, поэтому пока отналедовался просто от Fragment
class SettingsFragment : Fragment() {

    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var settingsStore: StoreSettings
    private lateinit var settingsScopeInstance: Scope

    companion object {
        // TODO: (Вячеслав): как используется newInstance() у фрагментов?
        fun newInstance() = SettingsFragment()
    }

    private fun initFragment() {
        childFragmentManager.beginTransaction()                                                     // загружаем AppBarTop
            .add(
                R.id.containerAppBar, AppBarTopMainFragment.newInstance(
                    "Настройки",
                    com.example.config.Const.APP_BAR_ONLY_BACK,
                    "",
                    ""
                )
            )
            .commitNow()
        with(binding.nightThemeSwitch) {
            isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
            setOnCheckedChangeListener { _, isSwitchChecked -> settingsStore.switchColorTheme(isNightThemeEnabled = isSwitchChecked) }
        }
    }

    private fun initObserve() {
        settingsStore.getLiveSettings().observe(viewLifecycleOwner, { isNightThemeEnabled: Boolean ->

            if (isNightThemeEnabled) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingsScopeInstance = KoinJavaComponent.getKoin().getOrCreateScope("settingsScopeId", named("SETTINGS_STORE"))
        settingsStore = settingsScopeInstance.get()
        initFragment()
        initObserve()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        settingsScopeInstance.close()
    }
}