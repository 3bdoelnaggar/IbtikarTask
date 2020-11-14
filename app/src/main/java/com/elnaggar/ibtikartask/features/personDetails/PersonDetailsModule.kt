package com.elnaggar.ibtikartask.features.personDetails

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val personDetailsModule= module {
    viewModel { PersonDetailsViewModel(get()) }
}