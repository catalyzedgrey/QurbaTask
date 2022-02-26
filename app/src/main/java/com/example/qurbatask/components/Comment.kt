package com.example.qurbatask.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.qurbatask.data.CommentItem
import com.example.qurbatask.ui.theme.TextBubble

@Composable
fun Comment(modifier: Modifier = Modifier, commentItem: CommentItem) {

    ConstraintLayout(Modifier.padding(start = 16.dp, end = 16.dp, top = 12.5.dp)) {
        val (profilePic, profileName, commentText, commentTextBubble, reaction) = createRefs()

        RoundedImage(
            Modifier.constrainAs(profilePic) {
                start.linkTo(parent.start)
            },
            stringResourceId = commentItem.profilePic
        )
        Surface(
            modifier
                .constrainAs(
                    commentTextBubble
                ) {
                    start.linkTo(profilePic.end, 8.dp)
                },
            color = TextBubble,
            shape = RoundedCornerShape(13.dp)
        ) {
            Column(Modifier.padding(12.dp)) {
                Text(text = commentItem.username)
                Text(
                    modifier = Modifier.padding(top = 2.dp), text = commentItem.commentMessage
                )
            }
        }

        ReactionComment(
            modifier.constrainAs(reaction) {
                start.linkTo(profilePic.end, 16.dp)
                top.linkTo(commentTextBubble.bottom, 5.dp)
            },
            commentItem = commentItem
        )
    }
}