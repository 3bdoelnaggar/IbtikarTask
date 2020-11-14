package com.elnaggar.ibtikartask.features.popularPeopleList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elnaggar.ErrorResult
import com.elnaggar.Success
import com.elnaggar.data.repositories.TmdbDataSource
import kotlinx.coroutines.launch


sealed class PopularPeopleListState() {
    object Error : PopularPeopleListState()
    data class Success(val data: List<UiPeople> = emptyList()) : PopularPeopleListState()
}

class PopularPeopleListViewModel(private val tmdbDataSource: TmdbDataSource) : ViewModel() {
    private val _stateLiveData = MutableLiveData<PopularPeopleListState>()
    val stateLiveData: LiveData<PopularPeopleListState> = _stateLiveData

    fun getPopularPeopleList(page: Int = 1) {


        val currentState = stateLiveData.value
        viewModelScope.launch {
            when (val result = tmdbDataSource.getPopularPerson(page)) {
                is Success ->
                    when (currentState) {
                        PopularPeopleListState.Error, null -> {
                            val newItems = result.data.map {
                                UiPeople(it.id.toLong(), it.profilePath, it.name)
                            }
                            val newState = PopularPeopleListState.Success(newItems)
                            _stateLiveData.value = newState
                        }
                        is PopularPeopleListState.Success -> {
                            val newItems = result.data.map {
                                UiPeople(it.id.toLong(), it.profilePath, it.name)
                            }
                            val newList = currentState.data.toMutableList()
                            newList.addAll(newItems)
                            val newState = PopularPeopleListState.Success(newList)
                            _stateLiveData.value = newState

                        }

                    }


                is ErrorResult -> PopularPeopleListState.Error
            }

        }
    }
}