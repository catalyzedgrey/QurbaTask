package com.example.qurbatask.data

data class FeedItem(
    val profilePic: Int,
    val name: String,
    val badge: String?,
    val badgeIcon: Int?,
    val date: String,
    val postText: String,
    var imageResourceIdList: ArrayList<Int>,
    val reaction: Reaction?,
    val comments: List<CommentItem>?,
    val hasMenuItem: Boolean = false,
    val hasRetweet: Boolean,
    val feedItem: FeedItem?,
    val restaurantInfo: RestaurantInfo?
)