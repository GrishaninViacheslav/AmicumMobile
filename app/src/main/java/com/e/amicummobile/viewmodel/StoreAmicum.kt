package com.e.amicummobile.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.amicummobile.config.Bootstrap
import com.e.amicummobile.config.Const
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
    private val repositoryImpl: IRepository = RepositoryImpl(),
    private var statusAuthorization: Boolean = false                                                                    // статус авторизации

) : ViewModel() {

    // GETTER
    fun getUserSession() =
        userSession                                                                                  // получение объекта сессии

    fun getStatusAuthorization() =
        statusAuthorization                                                                  // получение статуса авторизации

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
        statusAuthorization = false

        if (login == "1" && pwd == "1" && Bootstrap.TYPE_BUILD == Const.VERSION_DEBUG) {                                 // TODO отладочный костыль убрать в релизе
            statusAuthorization = true
        }

        val payload = UserAutorizationActionLoginRequest(
            login = login,
            password = pwd,
            activeDirectoryFlag = typeAuthorization
        )

        val jsonString: String = Assistant.toJson(payload)

        val config = ConfigToRequest(
            "UserAutorization",
            "actionLogin",
            "",
            jsonString
        )
        repositoryImpl.getData(config)

        return statusAuthorization
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
        var statusSession = false
        if (userSession.value != null && userSession.value?.workerId != -1) {
            statusSession = true
        }
        return statusSession
    }


}