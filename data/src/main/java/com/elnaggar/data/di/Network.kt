package com.elnaggar.data.di


import com.elnaggar.data.TmdbService
import com.elnaggar.data.repositories.TmdbDataSource
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { okHttpClient() }
    single { moshi() }
    single { retrofit("https://api.themoviedb.org/3/", get(), get()) }
    single { tmdbService(get()) }
    single { TmdbDataSource(get()) }
}



private fun okHttpClient(
): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.connectTimeout(5, TimeUnit.MINUTES)
    client.callTimeout(5, TimeUnit.MINUTES)
    client.readTimeout(5, TimeUnit.MINUTES)
    client.writeTimeout(5, TimeUnit.MINUTES)
    return client.build()
}

private fun moshi(): Moshi {
    return Moshi.Builder()
        .build()
}

fun retrofit(baseUrl: String, okHtpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHtpClient)
        .build()
}

fun tmdbService(retrofit: Retrofit): TmdbService {
   return retrofit.create(TmdbService::class.java)
}


