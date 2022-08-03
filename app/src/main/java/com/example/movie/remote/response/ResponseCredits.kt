package com.example.movie.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseCredits(
    val cast: ArrayList<CastItem>? = null,
    val id: Int? = null,
    val crew: ArrayList<CrewItem>? = null
) : Parcelable

@Parcelize
data class CastItem(
    val castId: Int? = null,
    val character: String? = null,
    val gender: Int? = null,
    val creditId: String? = null,
    val knownForDepartment: String? = null,
    val originalName: String? = null,
    val popularity: Double? = null,
    val name: String? = null,
    val profile_path: String? = null,
    val id: Int? = null,
    val adult: Boolean? = null,
    val order: Int? = null
) : Parcelable

@Parcelize
data class CrewItem(
    val gender: Int? = null,
    val creditId: String? = null,
    val knownForDepartment: String? = null,
    val originalName: String? = null,
    val popularity: Double? = null,
    val name: String? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null,
    val id: Int? = null,
    val adult: Boolean? = null,
    val department: String? = null,
    val job: String? = null
) : Parcelable
