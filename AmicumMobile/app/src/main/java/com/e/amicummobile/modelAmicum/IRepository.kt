package com.e.amicummobile.modelAmicum

interface IRepository {
    fun getData(configToRequest: ConfigToRequest): JsonFromServer                                                       // главный метод получения данных
    fun getDataFromRemoteServer(configToRequest: ConfigToRequest): JsonFromServer                                       // получение данных с удаленного сервера
    fun getDataFromLocalServer(configToRequest: ConfigToRequest): JsonFromServer                                        // получение данных с локального сервера
    fun getDataFromLocalStorage(configToRequest: ConfigToRequest): JsonFromServer                                       // получение данных с локального хранилища приложения
    fun getDataTest(): JsonFromServer                                                                                   // получение тестовых данных
}
