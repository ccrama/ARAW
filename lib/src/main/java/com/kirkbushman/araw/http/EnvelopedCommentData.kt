package com.kirkbushman.araw.http

import com.kirkbushman.araw.http.base.EnvelopeKind
import com.kirkbushman.araw.models.mixins.CommentData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
open class EnvelopedCommentData(

    @Json(name = "kind")
    override val kind: EnvelopeKind,

    @Json(name = "data")
    override val data: CommentData

) : EnvelopedContribution(kind, data), Serializable
