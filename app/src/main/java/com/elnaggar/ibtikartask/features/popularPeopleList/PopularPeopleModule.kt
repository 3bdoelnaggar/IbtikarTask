package com.elnaggar.ibtikartask.features.popularPeopleList

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val popularPeopleModule= module {
    viewModel { PopularPeopleListViewModel(get()) }
}