package com.e.amicummobile.modelAmicum


interface IRepository {
    suspend fun getData(configToRequest: ConfigToRequest): String                                   // главный метод получения данных
}
