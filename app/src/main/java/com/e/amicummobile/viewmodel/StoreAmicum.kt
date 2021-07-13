package com.e.amicummobile.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.amicummobile.controller.Assistant
import com.e.amicummobile.modelAmicum.ConfigToRequest
import com.e.amicummobile.modelAmicum.IRepository
import com.e.amicummobile.modelAmicum.RepositoryImpl
import com.e.amicummobile.modelAmicum.UserSession
import com.google.gson.GsonBuilder


/**
 * Главное хранилище системы АМИКУМ:
 *  - справочники системы
 *  - сведения о пользователе/сессия
 *  - выбор меню системы (дата, подразделение, шахта и т.д.)
 */
class StoreAmicum(

    // STATE
    private val userSession: MutableLiveData<UserSession> = MutableLiveData(),
    private val repositoryImpl: IRepository = RepositoryImpl()

) : ViewModel() {

    // GETTER
    fun getUserSession() = userSession

    // ACTION

    /**
     * Метод авторизации пользователя на сервер и получения сессионных данных о нем
     */
    fun getLogin(login: String, pwd: String, typeAuthorization: Boolean): Boolean {
        Log.println(Log.INFO, "storeAmicum.getLogin", "Запрос авторизации на сервере")
        Log.println(
            Log.INFO,
            "storeAmicum.getLogin",
            "login: " + login + " pwd: " + pwd + " typeAuthorization: " + typeAuthorization
        )
        var statusAutorization = false
        val payload: UserAutorizationActionLoginRequest = UserAutorizationActionLoginRequest(
            login = login,
            password = pwd,
            activeDirectoryFlag = typeAuthorization
        )

        val jsonString: String = Assistant.toJson(payload)

        val config: ConfigToRequest = ConfigToRequest(
            "UserAutorization",
            "actionLogin",
            "",
            jsonString
        )
        repositoryImpl.getData(config)

        return statusAutorization
    }

    data class UserAutorizationActionLoginRequest(
        val login: String,
        val password: String,
        val activeDirectoryFlag: Boolean
    )

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