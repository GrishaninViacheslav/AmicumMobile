package com.e.amicummobile.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.amicummobile.modelAmicum.UserSession
import com.e.amicummobile.modelAmicum.Repository
import com.e.amicummobile.modelAmicum.RepositoryImpl

/**
 * Главное хранилище системы АМИКУМ:
 *  - справочники системы
 *  - сведения о пользователе/сессия
 *  - выбор меню системы (дата, подразделение, шахта и т.д.)
 */
class StoreAmicum(

    // STATE
    private val userSession: MutableLiveData<UserSession> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()

) : ViewModel() {

    // GETTER
    fun getUserSession() = userSession

    // ACTION
    fun getDataFromLocalSource() {
        Log.println(Log.ERROR, "storeAmicum.getDataFromLocalSource", "получил данные с сервера")
    }

    fun getDataFromRemoteSource() {
        Log.println(Log.ERROR, "storeAmicum.getDataFromRemoteSource", "получил данные с сервера")
    }

    /**
     * Метод проверки наличия авторизации пользователя на сервере
     */
    fun checkUserSession(): Boolean {
        var statusSession: Boolean = false;
        if (userSession.value != null && userSession.value?.workerId != -1) {
            statusSession = true;
        }
        return statusSession;
    }


}