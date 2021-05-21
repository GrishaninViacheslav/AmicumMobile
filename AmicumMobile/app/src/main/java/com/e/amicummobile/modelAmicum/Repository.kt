package com.e.amicummobile.modelAmicum

interface Repository {
    fun getDataFromRemoteServer(): JsonFromServer                                                                       // получение данных с удаленного сервера
    fun getDataFromLocalServer(): JsonFromServer                                                                        // получение данных с локального сервера
    fun getDataFromLocalStorage(): JsonFromServer                                                                       // получение данных с локального хранилища приложения
    fun getDataTest(): JsonFromServer                                                                                   // получение тестовых данных
}
