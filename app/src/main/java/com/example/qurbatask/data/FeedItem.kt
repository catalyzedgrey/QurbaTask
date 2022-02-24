package com.example.qurbatask.data

data class FeedItem(
    val name: String,
    val badge: String?,
    val date: String,
    val postText: String,
    val imageResourceIdList: ArrayList<Int>,
    val reaction: Reaction,
    val IsRetweet: Boolean,
    val feedItem: FeedItem?
)