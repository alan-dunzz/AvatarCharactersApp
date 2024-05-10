package com.example.ejercicio2.network

import com.example.ejercicio2.model.AvatarDetailDto
import com.example.ejercicio2.model.AvatarDto
import com.example.ejercicio2.util.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


private val retrofit = Retrofit.Builder()
    //.addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(Constants.BASE_URL)
    .build()

interface AvatarApiService {

    //https://last-airbender-api.fly.dev/api/v1/characters?perPage=497
    @GET("api/v1/characters?perPage=497")
    fun getCharacters(): Call<List<AvatarDto>>

    //https://last-airbender-api.fly.dev/api/v1/characters/5cf5679a915ecad153ab68d6
    @GET("api/v1/characters/{id}")
    fun getCharacterDetail(
        @Path("id") id: String
    ): Call<AvatarDetailDto>
}

object AvatarApi{
    val retrofitService: AvatarApiService by lazy{
        retrofit.create(AvatarApiService::class.java)
    }
}
