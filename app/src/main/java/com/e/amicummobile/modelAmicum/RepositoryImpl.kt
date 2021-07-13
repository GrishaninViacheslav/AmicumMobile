package com.e.amicummobile.modelAmicum

import android.util.Log
import com.e.amicummobile.config.Bootstrap
import com.e.amicummobile.config.Const

/**
 * Репозиторий системы, содержит три способа получения данных:
 *  - с удаленного сервера
 *  - с локального сервера
 *  - с локальной БД мобильного приложения
 *  - с тестовых данных
 */
class RepositoryImpl : IRepository {

    init {

    }

    override fun getDataFromRemoteServer(configToRequest: ConfigToRequest): JsonFromServer {
        return JsonFromServer()
    }

    override fun getDataFromLocalServer(configToRequest: ConfigToRequest): JsonFromServer {
        return JsonFromServer()
    }

    override fun getDataFromLocalStorage(configToRequest: ConfigToRequest): JsonFromServer {
        when (configToRequest.method) {
            "actionLogin" -> {
                var response = JsonFromServer()
                response.Items.add("true")
                return response
            }
        }
        return JsonFromServer()
    }

    override fun getDataTest(): JsonFromServer {
        return JsonFromServer()
    }

    override fun getData(configToRequest: ConfigToRequest): JsonFromServer {
        Log.println(Log.ERROR, "storeAmicum.getData", "запрос данных с сервера")
        when (Bootstrap.DEFAULT_REQUEST_METHOD) {
            Const.LOCAL_REQUEST_METHOD -> getDataFromLocalStorage(configToRequest)
            Const.SERVER_LOCAL_REQUEST_METHOD -> getDataFromLocalServer(configToRequest)
            Const.SERVER_REMOTE_REQUEST_METHOD -> getDataFromRemoteServer(configToRequest)
        }
        return JsonFromServer()
    }
}
