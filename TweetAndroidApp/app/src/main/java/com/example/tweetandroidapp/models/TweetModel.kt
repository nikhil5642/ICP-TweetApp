package com.example.tweetandroidapp.models

import org.ic4j.candid.annotations.Field
import org.ic4j.candid.annotations.Name
import org.ic4j.candid.types.Type
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
){
    fun getFormattedTime(): String? {
        val date = createdAt?.let { Date(it) }
        val format = SimpleDateFormat("h:mm a · MMM d, yyyy", Locale.getDefault())
        return date?.let { format.format(it) }
    }
}
