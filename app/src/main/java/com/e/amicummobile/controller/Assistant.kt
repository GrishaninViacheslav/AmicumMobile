package com.e.amicummobile.controller

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject

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

    /**
     * Преобразование объекта полученного с сервера из JSON строки
     */
    fun fromJson(jsonString: String): JsonObject {
        val root = GsonBuilder().create().fromJson(jsonString, JsonObject::class.java)
        return root["Items"].asJsonObject
    }
}