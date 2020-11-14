package com.elnaggar.ibtikartask.features.popularPeopleList

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.elnaggar.ibtikartask.databinding.PopularPeopleListActivityBinding
import com.elnaggar.ibtikartask.features.personDetails.PersonDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
const val PERSON_ID_KEY = "person_id_key"
class PopularPeopleListActivity : AppCompatActivity() {

    lateinit var binding: PopularPeopleListActivityBinding

    private val popularPeopleListViewModel: PopularPeopleListViewModel by viewModel()

    private val popularPeopleController by lazy {
        PopularPeopleController(::onItemClicked)
    }
    private var page = 1

    private fun onItemClicked(id: Long) {
        val intent = Intent(this, PersonDetailsActivity::class.java)
        intent.putExtra(PERSON_ID_KEY,id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PopularPeopleListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.popularListRc.setController(popularPeopleController)

        popularPeopleListViewModel.getPopularPeopleList()
        popularPeopleListViewModel.stateLiveData.observe(this) {
            when (it) {
                PopularPeopleListState.Error -> {


                }
                is PopularPeopleListState.Success -> popularPeopleController.setData(it.data)

            }

        }

        binding.popularListRc.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (recyclerView.canScrollVertically(1)
                        .not() && newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    page++
                    popularPeopleListViewModel.getPopularPeopleList(page)


                }
            }
        })

    }
}