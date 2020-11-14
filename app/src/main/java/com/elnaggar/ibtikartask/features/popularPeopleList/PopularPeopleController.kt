package com.elnaggar.ibtikartask.features.popularPeopleList

import com.airbnb.epoxy.TypedEpoxyController


data class UiPeople(val id: Long, val profile: String, val name: String)
class PopularPeopleController(private val onItemClicked: (id: Long) -> Unit) :
    TypedEpoxyController<List<UiPeople>>() {
    override fun buildModels(data: List<UiPeople>) {
        data.forEach {
            peopleView {
                id(it.id)
                people(it)
                itemClickListener {
                    onItemClicked.invoke(it.id)
                }

            }
        }
    }
}
