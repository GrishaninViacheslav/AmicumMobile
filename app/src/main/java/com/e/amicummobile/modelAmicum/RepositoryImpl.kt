package com.e.amicummobile.modelAmicum

import android.util.Log
import com.e.amicummobile.config.Bootstrap
import com.e.amicummobile.config.Const
import com.e.amicummobile.controller.Assistant.fromJson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject

/**
 * Репозиторий системы, содержит три способа получения данных:
 *  - с удаленного сервера
 *  - с локального сервера
 *  - с локальной БД мобильного приложения
 *  - с тестовых данных
 */
class RepositoryImpl : IRepository {

    init {

    }

    override fun getDataFromRemoteServer(configToRequest: ConfigToRequest): String {
        return ""
    }

    override fun getDataFromLocalServer(configToRequest: ConfigToRequest): String {
        return ""
    }

    override fun getDataFromLocalStorage(configToRequest: ConfigToRequest): String =
        when (configToRequest.method) {
            "actionLogin" -> {

                "{\"Items\":{\"sessionLogin\":\"root\",\"sessionPassword\":\"\",\"userWorkstation\":\"Администратор\",\"employee_id\":1,\"userName\":\"Не заполнено. Н. Н.\",\"last_name\":\"Не заполнено\",\"first_name\":\"Не заполнено\",\"patronymic\":\"Не заполнено\",\"gender\":\"М\",\"birthdate\":\"1970-01-01\",\"position_id\":1,\"position_title\":\"Прочее\",\"position_qualification\":null,\"worker_date_start\":\"2019-10-09\",\"worker_date_end\":\"2099-12-31\",\"userShift\":\"\",\"userCompany\":\"Прочее\",\"userCompanyId\":101,\"userMineId\":290,\"userMineTitle\":\"Заполярная-2\",\"mineCompanyId\":501,\"userDepartmentId\":1,\"userDepartmentTitle\":\"Прочее\",\"userDepartmentPath\":\"Прочее / \",\"userDepartmentTypeId\":2,\"userCompanyDepartmentId\":101,\"userWorkCompanyDepartmentId\":101,\"worker_id\":1,\"user_id\":1,\"workerObject_ids\":[100000009,1],\"userStaffNumber\":\"1\",\"tabel_number\":\"1\",\"session_id\":\"7ko4mjhhg4im3t06fsj75k930q\",\"worker_role\":{\"9\":\"Прочее\",\"172\":\"Гл. геолог\"},\"socket_key\":\"F60ElN8VMFF4047UlYub\"},\"status\":1,\"errors\":[],\"warnings\":[],\"debug\":[],\"debug_data\":[]}"

            }
            "GetNotificationAll" -> {
                "{\"Items\":[{\"id\":\"siz\",\"title\":\"Необходима замена СИЗ\",\"notifications\":[{\"worker_id\":17,\"worker_full_name\":\"Прунька Иван Иванович\",\"worker_staff_number\":\"15-таб\",\"worker_position_title\":\"Главный инженер\",\"checkup_date_start\":\"2021-07-14\",\"checkup_date_end\":\"2021-07-21\",\"flag\":\"true\",\"status_id\":1,\"siz_id\":\"12\",\"siz_title\":\"Тапки\"},{\"worker_id\":18,\"worker_full_name\":\"Сизов Иван Иванович\",\"worker_staff_number\":\"16-таб\",\"worker_position_title\":\"Главный механик\",\"checkup_date_start\":\"2021-07-13\",\"checkup_date_end\":\"2021-07-20\",\"flag\":\"false\",\"status_id\":19,\"siz_id\":\"13\",\"siz_title\":\"Сапоги\"}]},{\"id\":\"medicalExam\",\"title\":\"Запланированный медицинский осмотр\",\"notifications\":[{\"worker_id\":15,\"worker_full_name\":\"Пупкин Иван Иванович\",\"worker_staff_number\":\"15-таб\",\"worker_position_title\":\"Главный инженер\",\"checkup_date_start\":\"2021-07-14\",\"checkup_date_end\":\"2021-07-21\",\"flag\":\"true\",\"status_id\":1,\"siz_id\":\"\",\"siz_title\":\"\"},{\"worker_id\":16,\"worker_full_name\":\"Иванов Иван Иванович\",\"worker_staff_number\":\"16-таб\",\"worker_position_title\":\"Главный механик\",\"checkup_date_start\":\"2021-07-13\",\"checkup_date_end\":\"2021-07-20\",\"flag\":\"false\",\"status_id\":19,\"siz_id\":\"\",\"siz_title\":\"\"}]}],\"errors\":[],\"status\":1,\"warnings\":[]}"
            }
            else -> ""
        }


    override fun getDataTest(configToRequest: ConfigToRequest): String {
        return ""
    }

    override fun getData(configToRequest: ConfigToRequest): String =

        when (Bootstrap.DEFAULT_REQUEST_METHOD) {
            Const.LOCAL_REQUEST_METHOD -> getDataFromLocalStorage(configToRequest)
            Const.SERVER_LOCAL_REQUEST_METHOD -> getDataFromLocalServer(configToRequest)
            Const.SERVER_REMOTE_REQUEST_METHOD -> getDataFromRemoteServer(configToRequest)
            else -> getDataTest(configToRequest)
        }

}
