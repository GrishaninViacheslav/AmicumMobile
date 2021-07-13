package com.e.amicummobile.config

/**
 * Константы приложения
 */
object Const {
    // тип запроса данных
    const val LOCAL_REQUEST_METHOD =
        1                                                                                  // локальные данные
    const val SERVER_REMOTE_REQUEST_METHOD =
        2                                                                          // данные с внешней сети сервера
    const val SERVER_LOCAL_REQUEST_METHOD =
        3                                                                           // данные с внутренней сети сервера

    // тип сборки проекта
    const val VERSION_PRODUCTION =
        1                                                                                    // версия продуктив
    const val VERSION_QAS =
        2                                                                                           // версия опытная/проверка качества
    const val VERSION_DEBUG =
        2                                                                                         // версия тестовая
}