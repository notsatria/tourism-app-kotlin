package com.notsatria.tourismapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    val image: Int,
    val name: String,
    val country: String,
    val rating: String,
    val description: String,
) : Parcelable
