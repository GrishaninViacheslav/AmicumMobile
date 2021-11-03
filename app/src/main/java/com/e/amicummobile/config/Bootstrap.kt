package com.e.amicummobile.config

import com.e.amicummobile.config.Const

/**
 * Настройки приложения
 */
object Bootstrap {
    // подключение к серверу
    var REMOTE_SERVER_IP = "46.181.246.234"                                                         // IP адрес внешнего сервера
    var REMOTE_SERVER_PORT = "7777"                                                                 // порт внешнего сервера

    var LOCAL_SERVER_IP = "192.168.1.5"                                                             // IP адрес локального сервера
    var LOCAL_SERVER_PORT = "80"                                                                    // Порт локального сервера

    var TYPE_BUILD = Const.VERSION_DEBUG                                                            // тип сборки (продуктив, проверка производительности, тестовая)

    var DEFAULT_REQUEST_METHOD = Const.LOCAL_REQUEST_METHOD                                         // вариант подключения
    var DEFAULT_URL_ADDRESS = REMOTE_SERVER_IP + ":" + REMOTE_SERVER_PORT                           // адрес сервера
}