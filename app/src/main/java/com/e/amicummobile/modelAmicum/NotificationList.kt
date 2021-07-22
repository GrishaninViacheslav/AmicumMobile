package com.e.amicummobile.modelAmicum

/**
 * Клсаа уведомлений - элемент
 * Notifications:[
 *      {
 *          id:                 "medicalExam"
 *          title:              "Запланированный медицинский осмотр"
 *          notifications:      NotificationMedicalExamWorker,
 *      }
 *  ]
 */
class NotificationList<T>(
    var id: String,
    var title: String,
    var notifications: ArrayList<T>
)

/**
 * Класс уведомления
 */
data class Notification(
    val worker_id: Int,                     // ключ работника
    val worker_full_name: String,           // ФИО
    val worker_staff_number: String,        // табельный номер работника
    val worker_position_title: String,      // должность
    val siz_id: String,                     // ключ СИЗ
    val siz_title: String,                  // название СИЗ
    val checkup_date_start: String,         // дата начала медосмотра
    val checkup_date_end: String,           // дата окончания медосмотра
    val flag: Boolean,                      // true  - если до окончания срока медосмотра осталось 2 недели или менее, то возвращается ораньжевый цвет. false - иначе срок замены просрочен, то возвращается красный цвет. null  - во всех остальных случаях
    val status_id: Int,                     // статус уведомления (прочитан-19 или нет-1)
)

