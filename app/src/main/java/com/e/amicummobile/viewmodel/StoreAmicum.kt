package com.e.amicummobile.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.e.amicummobile.config.Const
import com.e.amicummobile.controller.Assistant
import com.e.amicummobile.interactor.MainInteractor
import com.e.amicummobile.modelAmicum.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Главное хранилище системы АМИКУМ:
 *  - справочники системы
 *  - сведения о пользователе/сессия
 *  - выбор меню системы (дата, подразделение, шахта и т.д.)
 */
class StoreAmicum(

    // STATE
    private val interactor: MainInteractor,
    private var userSession: MutableLiveData<UserSession> = MutableLiveData(),
    private var notificationAll: MutableLiveData<ArrayList<NotificationList<Notification>>> = MutableLiveData(),              // список уведомлений пользователя

) : BaseViewModel() {

    // GETTER
    fun getUserSession() = userSession                                                              // получение объекта сессии
    fun getNotificationAll() = notificationAll                                                      // получение всех уведомлений пользователя
    fun getNotificationPersonal(): MutableLiveData<ArrayList<NotificationList<Notification>>> {     // получение персональных уведомлений пользователя
        return notificationAll
    }

    // ACTION

    /**
     * Метод авторизации пользователя на сервер и получения сессионных данных о нем
     */
    fun getLogin(login: String, pwd: String, typeAuthorization: Boolean) {
        Log.println(Log.INFO, "storeAmicum.getLogin", "Запрос авторизации на сервере")
        Log.println(
            Log.INFO,
            "storeAmicum.getLogin",
            "login: " + login + " pwd: " + pwd + " typeAuthorization: " + typeAuthorization
        )

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

        cancelJob()
        viewModelCoroutineScope.launch {
            requestSession(config, Const.TEST_REQUEST_METHOD)
        }
    }


    /**
     * Метод получения всех уведомлений
     */
    fun getNotification(companyId: Int?): MutableLiveData<ArrayList<NotificationList<Notification>>> {
        Log.println(Log.INFO, "storeAmicum.getNotification", "Запрос уведомлений на сервере")
        Log.println(Log.INFO, "storeAmicum.getNotification", "companyId: " + companyId)

        val payload = NotificationAllRequest(
            company_id = companyId
        )

        val jsonString: String = Assistant.toJson(payload)

        val config = ConfigToRequest(
            "notification\\Notification",
            "GetNotificationAll",
            "",
            jsonString
        )

        cancelJob()
        viewModelCoroutineScope.launch { requestNotification(config, Const.TEST_REQUEST_METHOD) }


        Log.println(Log.INFO, "storeAmicum.getNotification", "Закончил выполнение: ")

        return notificationAll
    }

    /**
     * класс запроса авторизации пользователя
     */
    data class UserAutorizationActionLoginRequest(
        val login: String,
        val password: String,
        val activeDirectoryFlag: Boolean
    )

    /**
     * класс запроса уведомлений
     */
    data class NotificationAllRequest(
        val company_id: Int?
    )

    /**
     * Метод проверки наличия авторизации пользователя на сервере
     */
    fun checkUserSession(): Boolean {
        var statusSession = false
        if (userSession.value != null && userSession.value?.worker_id != -1) {
            statusSession = true
        }
        return statusSession
    }

    private suspend fun requestSession(configToRequest: ConfigToRequest, isOnline: String) =
        withContext(Dispatchers.IO) {
            class Token : TypeToken<JsonFromServer<UserSession>>()

            val response = interactor.getData(configToRequest, isOnline)
            val temp: JsonFromServer<UserSession> = Gson().fromJson(response, Token().type)
            userSession.postValue(temp.getItems())
        }


    private suspend fun requestNotification(configToRequest: ConfigToRequest, isOnline: String) =
        withContext(Dispatchers.IO) {
            class Token : TypeToken<JsonFromServer<ArrayList<NotificationList<Notification>>>>()

            val response = interactor.getData(configToRequest, isOnline)
            val temp: JsonFromServer<ArrayList<NotificationList<Notification>>> = Gson().fromJson(response, Token().type)
            notificationAll.postValue(temp.getItems())
        }


    // Обрабатываем ошибки
    override fun handleError(error: Throwable) {
        Log.println(
            Log.ERROR,
            "storeAmicum.handleError",
            error.message.toString()
        )
    }

    override fun onCleared() {
        super.onCleared()
    }
}