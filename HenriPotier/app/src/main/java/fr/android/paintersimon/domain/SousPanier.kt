package fr.android.paintersimon.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SousPanier(val book: Book, var quantity: Int): Parcelable

