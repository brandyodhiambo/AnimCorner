package com.example.animconer.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Trailer(
    @SerializedName("url")
    val url: String?,
    @SerializedName("youtube_id")
    val youtubeId: String?
) :Parcelable