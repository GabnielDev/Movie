package com.example.movie.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseTrailer(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("results")
    val results: ArrayList<ResultTrailer>? = null
)

@Parcelize
data class ResultTrailer(

    @SerializedName("site")
    val site: String? = null,

    @SerializedName("size")
    val size: Int? = null,

    @SerializedName("iso_3166_1")
    val iso31661: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("official")
    val official: Boolean? = null,

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("published_at")
    val publishedAt: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("iso_639_1")
    val iso6391: String? = null,

    @SerializedName("key")
    val key: String? = null
):Parcelable
