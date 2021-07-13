package com.e.amicummobile.modelAmicum

/**
 * Модель данных приходящих с сервера
 */
class JsonFromServer {
    lateinit var Items: ArrayList<String>                                                                               // полезная нагрузка
    lateinit var errors: ArrayList<String>                                                                              // массив ошибок
    lateinit var debug: ArrayList<String>                                                                               // массив отладочной информации
    lateinit var debugData: ArrayList<String>                                                                           // массив отладочных данных
    var status: Int = 0                                                                                                 // статус выполнения метода на сервере





}