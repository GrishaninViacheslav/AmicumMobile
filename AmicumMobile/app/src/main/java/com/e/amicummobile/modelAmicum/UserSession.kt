package com.e.amicummobile.modelAmicum

/**
 * Модель пользователя, авторизованного в системе
 */
data class UserSession(
    var workerId: Int,                                                                                                  // ключ работника
    var workerFullName: String                                                                                          // полное ФИО работника
)
