package com.example.qurbatask

data class FeedItem(
    val name: String,
    val badge: String?,
    val date: String,
    val postText: String,
    val imageResourceIdList: ArrayList<Int>,
    val likeCount: Float,
    val commentCount: Int,
    val shareCount: Float,
    val IsRetweet: Boolean,
    val feedItem: FeedItem?
)