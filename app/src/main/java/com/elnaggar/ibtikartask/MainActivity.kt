package com.elnaggar.ibtikartask

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.elnaggar.data.repositories.TmdbDataSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    val tmdbDataSource: TmdbDataSource by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            val toString = tmdbDataSource.getPopularPerson(0).getOrThrow().toString()
            runOnUiThread {
                Toast.makeText(this@MainActivity, toString,Toast.LENGTH_SHORT).show()
            }
        }
    }
}