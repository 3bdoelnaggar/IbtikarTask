package com.elnaggar.ibtikartask.features.personDetails

import android.security.identity.AccessControlProfile
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elnaggar.ErrorResult
import com.elnaggar.Success
import com.elnaggar.data.IMAGE_BASE_URL
import com.elnaggar.data.entities.PersonDetails
import com.elnaggar.data.repositories.TmdbDataSource
import com.elnaggar.ibtikartask.features.popularPeopleList.PopularPeopleListState
import kotlinx.coroutines.launch

sealed class  PersonDetailsState{
    object Error :PersonDetailsState()
    data class Success(val profile: String,val name:String,val dateOfBirth:String,val placeOfBirth:String,val images:List<String> = emptyList()):PersonDetailsState()
}

class PersonDetailsViewModel(private val tmdbDataSource: TmdbDataSource):ViewModel() {
    private val _stateLiveData = MutableLiveData<PersonDetailsState>()
    val stateLiveData: LiveData<PersonDetailsState> = _stateLiveData


    fun getDetails(id:Long){
        viewModelScope.launch {
            val result=tmdbDataSource.getPersonDetails(id.toString())
            when(result){
                is Success -> {
                    _stateLiveData.value=PersonDetailsState.Success(IMAGE_BASE_URL+result.data.profilePath,result.data.name,result.data.birthday,result.data.placeOfBirth)
                    callForImages(id)
                }
                is ErrorResult -> PersonDetailsState.Error
            }

        }


    }

   private suspend fun callForImages(id: Long) {
       val result=tmdbDataSource.getPersonImages(id.toString())
       when(result){
           is Success -> {
               val currantState=_stateLiveData.value as PersonDetailsState.Success
               _stateLiveData.value=currantState.copy(images = result.data.profiles.map {
                   IMAGE_BASE_URL+it.filePath
               })

           }
           is ErrorResult -> {

           }
       }
    }


}