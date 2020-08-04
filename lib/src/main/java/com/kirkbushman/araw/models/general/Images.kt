package com.kirkbushman.araw.models.general

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Images(

    @Json(name = "id")
    val id: String?,

    @Json(name = "source")
    val source: ImageDetail,

    @Json(name = "resolutions")
    val resolutions: Array<ImageDetail>,

    @Json(name = "variants")
    val variants: ImageVariants?

) : Parcelable {

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {

        if (other == null) {
            return false
        }

        if (other !is Images) {
            return false
        }

        return id == other.id
    }
}

@JsonClass(generateAdapter = true)
@Parcelize
data class ImageDetail(

    @Json(name = "url")
    val url: String,

    @Json(name = "width")
    val width: Int,

    @Json(name = "height")
    val height: Int

) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class ImageVariants(

    @Json(name = "gif")
    val gif: Images?,

    @Json(name = "mp4")
    val mp4: Images?

) : Parcelable
