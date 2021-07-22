package com.e.amicummobile.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.amicummobile.config.Bootstrap
import com.e.amicummobile.config.Const
import com.e.amicummobile.controller.Assistant
import com.e.amicummobile.modelAmicum.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken


/**
 * Главное хранилище системы АМИКУМ:
 *  - справочники системы
 *  - сведения о пользователе/сессия
 *  - выбор меню системы (дата, подразделение, шахта и т.д.)
 */
class StoreAmicum(

    // STATE
    private var userSession: MutableLiveData<UserSession> = MutableLiveData(),
    private val repositoryImpl: IRepository = RepositoryImpl(),
    private var statusAuthorization: Boolean = false,                                               // статус авторизации
    private var notificationAll: MutableLiveData<NotificationAll> = MutableLiveData(),              // список уведомлений пользователя

) : ViewModel() {

    // GETTER
    fun getUserSession() = userSession                                                              // получение объекта сессии
    fun getStatusAuthorization() = statusAuthorization                                              // получение статуса авторизации
    fun getNotificationAll() = notificationAll                                                      // получение уведомлений пользователя

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

//        if (login == "1" && pwd == "1" && Bootstrap.TYPE_BUILD == Const.VERSION_DEBUG) {                                 // TODO отладочный костыль убрать в релизе
        if (Bootstrap.TYPE_BUILD == Const.VERSION_DEBUG) {
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
        val response = repositoryImpl.getData(config)

        class Token : TypeToken<JsonFromServer<UserSession>>()

        val temp: JsonFromServer<UserSession> = Gson().fromJson(response, Token().type)

        userSession = MutableLiveData(temp.getItems())

        return statusAuthorization
    }


    /**
     * Метод получения всех уведомлений
     */
    fun getNotification(companyId: Int?): MutableLiveData<NotificationAll> {
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
        val response = repositoryImpl.getData(config)

        class Token : TypeToken<JsonFromServer<NotificationAll>>()

        val temp: JsonFromServer<NotificationAll> = Gson().fromJson(response, Token().type)

        notificationAll = MutableLiveData(temp.getItems())

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
     * Класс уведомлений по всем направления
     */
    data class NotificationAll(
        val medicalExam: Map<String, NotificationMedicalExamWorker>,   // запланированный медицинский осмотр по подразделению
    )

    /**
     * Класс уведомления по запланированному медицинскому осмотру работника
     */
    data class NotificationMedicalExamWorker(
        val worker_id: Int,                     // ключ работника
        val worker_full_name: String,           // ФИО
        val worker_staff_number: String,        // табельный номер работника
        val worker_position_title: String,      // должность
        val checkup_date_start: String,         // дата начала медосмотра
        val checkup_date_end: String,           // дата окончания медосмотра
        val flag: Boolean,                      // true  - если до окончания срока медосмотра осталось 2 недели или менее, то возвращается ораньжевый цвет. false - иначе срок замены просрочен, то возвращается красный цвет. null  - во всех остальных случаях
        val status_id: Int,                     // статус уведомления (прочитан-19 или нет-1)
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


}