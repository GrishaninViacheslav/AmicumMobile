package com.e.amicummobile.repository.localRepository

import com.e.amicummobile.modelAmicum.ConfigToRequest
import com.e.amicummobile.repository.IRepository

class RoomRepository : IRepository {
    override suspend fun getData(configToRequest: ConfigToRequest): String {
        TODO("Not yet implemented")
    }
}