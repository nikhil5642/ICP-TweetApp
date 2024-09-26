package com.example.tweetandroidapp.models

import org.ic4j.candid.annotations.Field
import org.ic4j.candid.annotations.Name
import org.ic4j.candid.types.Type

data class TweetModel(
    @Field(Type.NAT)
    @Name("id")
    var id: Long? = null,

    @Field(Type.TEXT)
    @Name("content")
    var content: String? = null,

    @Field(Type.TEXT)
    @Name("creatorID")
    var creatorId: String? = null,

    @Field(Type.INT)
    @Name("createdAt")
    var createdAt: Long? = null
)
