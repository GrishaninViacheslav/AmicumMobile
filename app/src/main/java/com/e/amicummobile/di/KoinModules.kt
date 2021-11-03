package com.e.amicummobile.di

import com.e.amicummobile.config.Const.SERVER_LOCAL_REQUEST_METHOD
import com.e.amicummobile.config.Const.SERVER_REMOTE_REQUEST_METHOD
import com.e.amicummobile.interactor.MainInteractor
import com.e.amicummobile.localRepository.RoomRepository
import com.e.amicummobile.modelAmicum.IRepository
import com.e.amicummobile.remoteRepository.RetrofitImpl
import com.e.amicummobile.viewmodel.StoreAmicum
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * Общие для всего приложения инъекции
 */
val application = module {
    single<IRepository>(named(SERVER_REMOTE_REQUEST_METHOD)) { RetrofitImpl() }
    single<IRepository>(named(SERVER_LOCAL_REQUEST_METHOD)) { RoomRepository() }
}
val mainScreen = module {
    factory { MainInteractor(get(named(SERVER_REMOTE_REQUEST_METHOD)), get(named(SERVER_LOCAL_REQUEST_METHOD))) }

    factory { StoreAmicum(get()) }
}
