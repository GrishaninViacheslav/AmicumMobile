package com.e.amicummobile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.e.amicummobile.interactor.settings.SettingsInteractor

// TODO: (Вячеслав): не разобрался почему viewModel StoreNotification расположена не в этом пакете.
//              StoreSettings пока поместил тут.
class StoreSettings(
    private val interactor: SettingsInteractor,
    private var liveSettings: MutableLiveData<Boolean> = MutableLiveData() // (Вячеслав): Пока настройка только одна - включенная ночная тема или нет, поэтому
    //             в liveSettings только одно значение
) : BaseViewModel() {

    fun getLiveSettings(): LiveData<Boolean> = liveSettings

    fun switchColorTheme(isNightThemeEnabled: Boolean){
        liveSettings.value = isNightThemeEnabled
    }

    override fun handleError(error: Throwable) {
        TODO("Not yet implemented")
    }
}