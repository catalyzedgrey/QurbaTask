package com.example.qurbatask.data

data class CommentItem(
    val profilePic: Int,
    val username: String,
    val commentMessage: String,
    val dateCommentPosted: String,
    val reactionCount: Int
)
