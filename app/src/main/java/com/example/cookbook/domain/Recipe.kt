package com.example.cookbook.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    val header: String,
    val body: String
) : Parcelable