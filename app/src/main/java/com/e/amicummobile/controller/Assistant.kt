package com.e.amicummobile.controller

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

    fun convertDateToFront(dateFromBack: String, withTime: Boolean = false): String {
        val formatterFrom: DateTimeFormatter                                                        // шаблон для преобразования из строки в дату
        when (withTime) {
            false -> formatterFrom = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            true -> formatterFrom = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        }

        val formatterTo: DateTimeFormatter                                                          // шаблон для преобразования даты в нужный формат
        when (withTime) {
            false -> formatterTo = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            true -> formatterTo = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
        }

        return LocalDate.parse(dateFromBack, formatterFrom).format(formatterTo)
    }
}