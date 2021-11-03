package com.e.amicummobile.interactor

import com.e.amicummobile.modelAmicum.ConfigToRequest

interface IInteractor {
    /**
     * @param configToRequest - конфигурация запроса
     * @param modeRequest - режим запроса, локальная сеть, из базы, из внешней сети
     */
    suspend fun getData(configToRequest: ConfigToRequest, modeRequest: String): String

}