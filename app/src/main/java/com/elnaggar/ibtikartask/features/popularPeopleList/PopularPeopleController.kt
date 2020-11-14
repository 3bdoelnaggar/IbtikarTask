package com.elnaggar.ibtikartask.features.popularPeopleList

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController


data class UiPeople(val id:Long,val profile:String,val name:String)
class PopularPeopleController ():TypedEpoxyController<List<UiPeople>>(){
    override fun buildModels(data: List<UiPeople>) {
      data.forEach {
          peopleView {
              id(it.id)
              people(it)
          }
      }
    }
}
