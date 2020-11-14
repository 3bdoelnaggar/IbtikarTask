package com.elnaggar.ibtikartask.features.popularPeopleList

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.elnaggar.data.IMAGE_BASE_URL
import com.elnaggar.ibtikartask.databinding.PopularPeopleListItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class PeopleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    val binding: PopularPeopleListItemBinding =
        PopularPeopleListItemBinding.inflate(LayoutInflater.from(context), this)

    var itemClickListener: (() -> Unit)? = null
        @CallbackProp set


    @ModelProp
    fun setPeople(people:UiPeople) {
        binding.nameTv.text = people.name
        binding.picIv.load(IMAGE_BASE_URL + people.profile)

        binding.root.setOnClickListener {
            itemClickListener?.invoke()
        }
    }

}