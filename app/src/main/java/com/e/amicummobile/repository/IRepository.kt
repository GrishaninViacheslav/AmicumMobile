package com.e.amicummobile.repository

import com.e.amicummobile.modelAmicum.ConfigToRequest


interface IRepository {
    suspend fun getData(configToRequest: ConfigToRequest): String                                   // главный метод получения данных
}
