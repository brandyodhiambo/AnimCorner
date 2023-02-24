package com.example.animconer.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Images(
    @SerializedName("jpg")
    val jpg: Jpg,
    @SerializedName("webp")
    val webp: Webp,
) : Parcelable
