package com.e.amicummobile.localRepository

import com.e.amicummobile.modelAmicum.ConfigToRequest
import com.e.amicummobile.modelAmicum.IRepository

class RoomRepository : IRepository {
    override suspend fun getData(configToRequest: ConfigToRequest): String {
        TODO("Not yet implemented")
    }
}