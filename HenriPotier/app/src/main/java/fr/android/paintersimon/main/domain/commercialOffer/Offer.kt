package fr.android.paintersimon.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Offers(
    val offers: List<Offer>
)

data class Offer(
    val type: String,
    val value: Int,
    val sliceValue: Int? = null
)
