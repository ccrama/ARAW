package com.kirkbushman.araw.models

import android.os.Parcelable
import com.kirkbushman.araw.models.mixins.Thing
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * This class represent a trophy given to a redditor.
 *
 * @property id This item identifier, e.g. "8xwlg"
 *
 * @property fullname Fullname of comment, e.g. "t1_c3v7f8u"
 *
 * @property description description of the trophy, the reason why it was given
 *
 * @property url url of the image at source resolution.
 *
 * @property icon40 url of the image at 40x40 resolution.
 *
 * @property icon70 url of the image at 70x70 resolution.
 *
 */
@JsonClass(generateAdapter = true)
@Parcelize
data class Trophy(

    @Json(name = "id")
    override val id: String,

    @Json(name = "name")
    override val fullname: String,

    @Json(name = "description")
    val description: String?,

    @Json(name = "icon_70")
    val icon70: String,

    @Json(name = "icon_40")
    val icon40: String,

    @Json(name = "url")
    val url: String?

) : Thing, Parcelable
