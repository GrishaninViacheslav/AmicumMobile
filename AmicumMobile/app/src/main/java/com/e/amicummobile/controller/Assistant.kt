package com.e.amicummobile.controller

import com.google.gson.GsonBuilder

/**
 * Класс содержит типовые частоиспользуемые методы
 */
object Assistant {

    /**
     * Преобразование в объекта в JSON строку
     */
    fun toJson(payload: Any): String {
        return GsonBuilder().create().toJson(payload)
    }
}