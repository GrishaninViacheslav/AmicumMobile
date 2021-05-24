package com.e.amicummobile.modelAmicum

/**
 * Модель пользователя, авторизованного в системе
 */
data class UserSession(
    var workerId: Int = -1,                                                                                             // ключ работника
    var workerFullName: String = ""                                                                                     // полное ФИО работника
)
