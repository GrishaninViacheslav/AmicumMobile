package com.e.amicummobile.di

import androidx.room.Room
import com.e.amicummobile.controller.CoinImageLoader
import com.e.amicummobile.controller.network.Network
import com.e.amicummobile.db.AmicumDB
import com.e.amicummobile.interactor.MainInteractor
import com.e.amicummobile.repository.localRepository.RoomRepository
import com.e.amicummobile.repository.IRepositoryLocal
import com.e.amicummobile.repository.IRepositoryRemote
import com.e.amicummobile.repository.localRepository.TestDataRepository
import com.e.amicummobile.viewmodel.StoreAmicum
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Общие для всего приложения инъекции
 */
val application = module {
    single<IRepositoryRemote> { TestDataRepository() }                                              // тестовый репозиторий
//    single<IRepositoryRemote>() { RetrofitImpl() }                                                  // удаленный репозиторий
    single<IRepositoryLocal> { RoomRepository(get()) }                                              // локальный репозиторий
    single { CoinImageLoader(androidContext()) }                                                    // контекст приложения
    single { Network(androidContext()) }                                                            // проваерка состояния сети
}
val mainScreen = module {
    factory { MainInteractor(get(), get()) }

    factory { StoreAmicum(get(), get()) }                                                           // главное вьюмодель приложения - хранит справочники
}

val db = module {
    single { Room.databaseBuilder(get(), AmicumDB::class.java, "AmicumDB").build() }          // база данных
    single { get<AmicumDB>().handbooksDao() }                                                       // доступ к справочникам системы

}