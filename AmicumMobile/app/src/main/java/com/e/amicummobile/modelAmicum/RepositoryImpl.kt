package com.e.amicummobile.modelAmicum

/**
 * Репозиторий системы, содержит три способа получения данных:
 *  - с удаленного сервера
 *  - с локального сервера
 *  - с локальной БД мобильного приложения
 *  - с тестовых данных
 */
class RepositoryImpl : Repository {

    override fun getDataFromRemoteServer(): JsonFromServer {
        return JsonFromServer()
    }

    override fun getDataFromLocalServer(): JsonFromServer {
        return JsonFromServer()
    }

    override fun getDataFromLocalStorage(): JsonFromServer {
        return JsonFromServer()
    }

    override fun getDataTest(): JsonFromServer {
        return JsonFromServer()
    }
}
