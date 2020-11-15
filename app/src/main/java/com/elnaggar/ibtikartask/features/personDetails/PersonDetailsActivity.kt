package com.elnaggar.ibtikartask.features.personDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.elnaggar.ibtikartask.databinding.PersonDetailsActivityBinding
import com.elnaggar.ibtikartask.features.popularPeopleList.PERSON_ID_KEY
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonDetailsActivity : AppCompatActivity() {

    private val viewModel: PersonDetailsViewModel by viewModel()
    lateinit var binding: PersonDetailsActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PersonDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getLongExtra(PERSON_ID_KEY, 0L)
        if (id == 0L) {
            finish()
        } else {
            viewModel.getDetails(id)

        }

        viewModel.stateLiveData.observe(this) {
            when (it) {
                PersonDetailsState.Error -> TODO()
                is PersonDetailsState.Success -> {
                    binding.nameTv.text = it.name
                    binding.birthDateTv.text = it.dateOfBirth
                    binding.placeOfBirthTv.text=it.placeOfBirth
                    binding.picIv.load(it.profile)
                    if (it.images.isNotEmpty()) {
                        binding.imagesRc.adapter = ImagesAdapter(it.images)
                    }
                }
            }
        }

    }
}