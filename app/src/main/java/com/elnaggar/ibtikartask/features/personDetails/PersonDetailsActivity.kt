package com.elnaggar.ibtikartask.features.personDetails

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.elnaggar.ibtikartask.databinding.PersonDetailsActivityBinding
import com.elnaggar.ibtikartask.features.image.ImageActivity
import com.elnaggar.ibtikartask.features.popularPeopleList.PERSON_ID_KEY
import org.koin.androidx.viewmodel.ext.android.viewModel

const val IMAGE_URL_KEY = "image_url_key"


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
                    binding.placeOfBirthTv.text = it.placeOfBirth
                    binding.picIv.load(it.profile)
                    if (it.images.isNotEmpty()) {
                        binding.imagesRc.adapter = ImagesAdapter(it.images, ::onImageClicked)
                    }
                }
            }
        }

    }

    private fun onImageClicked(image: String) {
        val intent = Intent(this, ImageActivity::class.java)
        intent.putExtra(IMAGE_URL_KEY, image)
        startActivity(intent)

    }
}