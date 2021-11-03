package com.e.amicummobile.interactor

import com.e.amicummobile.config.Const.LOCAL_REQUEST_METHOD
import com.e.amicummobile.config.Const.SERVER_REMOTE_REQUEST_METHOD
import com.e.amicummobile.repository.localRepository.TestDataRepository
import com.e.amicummobile.modelAmicum.*
import com.e.amicummobile.repository.IRepository

/**
 * Итерактор запрашивает данные:
 *  - с удаленного сервера
 *  - с локальной БД мобильного приложения
 *  - с тестовых данных
 */
class MainInteractor(
    private val remoteRepository: IRepository,
    private val localRepository: IRepository
) : IInteractor {

    override suspend fun getData(configToRequest: ConfigToRequest, modeRequest: String): String =
        when (modeRequest) {
            LOCAL_REQUEST_METHOD -> remoteRepository.getData(configToRequest)                 // данные с локальной БД
            SERVER_REMOTE_REQUEST_METHOD -> localRepository.getData(configToRequest)          // данные с сервера
            else -> TestDataRepository.getData(configToRequest)                                     // тестовые данные
        }
}
