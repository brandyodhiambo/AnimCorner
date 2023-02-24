package com.example.animconer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val malId: Int,
    val name: String,
) : Parcelable
