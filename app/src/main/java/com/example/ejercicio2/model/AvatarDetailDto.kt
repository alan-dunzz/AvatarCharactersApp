package com.example.ejercicio2.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AvatarDetailDto(
    @SerializedName("allies")
    var allies: ArrayList<String>,
    @SerializedName("enemies")
    var enemies: ArrayList<String>,
    @SerializedName("_id")
    var id: String,
    @SerializedName("photoUrl")
    var image: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("gender")
    var gender: String?,
    @SerializedName("hair")
    var hair: String?,
    @SerializedName("weapon")
    var weapon: String?,
    @SerializedName("profession")
    var profession: String?,
    @SerializedName("position")
    var position: String?,
    @SerializedName("affiliation")
    var affiliation: String?,
    @SerializedName("first")
    var first: String?
)
