package com.e.amicummobile.view

import com.e.amicummobile.modelAmicum.AppState

interface IView<T> {
    fun renderData(appState: AppState<T>)
}