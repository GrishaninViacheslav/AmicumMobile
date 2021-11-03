package geekbrains.ru.translator.model.data.api

import com.e.amicummobile.modelAmicum.ConfigToRequest
import com.e.amicummobile.modelAmicum.JsonFromServer
import com.e.amicummobile.modelAmicum.Notification
import com.e.amicummobile.modelAmicum.NotificationList
import kotlinx.coroutines.Deferred
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("read-manager-amicum")
    fun getNotification(@Query("config") config: ConfigToRequest): Deferred<String>                                          // Обратите внимание, что метод теперь возвращает
// Deferred
}
