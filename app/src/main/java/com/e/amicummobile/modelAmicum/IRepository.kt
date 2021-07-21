package com.e.amicummobile.modelAmicum

import com.google.gson.JsonObject

interface IRepository {
    fun getData(configToRequest: ConfigToRequest): String                                   // главный метод получения данных
    fun getDataFromRemoteServer(configToRequest: ConfigToRequest): String                   // получение данных с удаленного сервера
    fun getDataFromLocalServer(configToRequest: ConfigToRequest): String                    // получение данных с локального сервера
    fun getDataFromLocalStorage(configToRequest: ConfigToRequest): String                   // получение данных с локального хранилища приложения
    fun getDataTest(configToRequest: ConfigToRequest): String                               // получение тестовых данных
}
