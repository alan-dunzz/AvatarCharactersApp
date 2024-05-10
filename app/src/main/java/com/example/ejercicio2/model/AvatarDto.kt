package com.example.ejercicio2.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AvatarDto(
    @SerializedName("_id")
    var id: String,
    @SerializedName("allies")
    var allies: ArrayList<String>,
    @SerializedName("enemies")
    var enemies: ArrayList<String>,
    @SerializedName("photoUrl")
    var image: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("affiliation" )
    var affiliation: String?
)
