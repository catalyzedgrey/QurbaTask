package com.example.qurbatask.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.qurbatask.R
import com.example.qurbatask.data.CommentItem
import com.example.qurbatask.data.FeedItem
import com.example.qurbatask.ui.theme.GradientLeft
import com.example.qurbatask.ui.theme.GradientRight

@Composable
fun ReactionMenu(modifier: Modifier = Modifier, feedItem: FeedItem) {
    ConstraintLayout(
        modifier.fillMaxWidth()
    ) {
        val gradient = Brush.horizontalGradient(
            colors = listOf(
                GradientLeft,
                GradientRight
            )
        )
        val (likeCount, likeIcon, commentCount,
            commentIcon, shareCount, shareIcon,
            topDivider, bottomDivider) = createRefs()
        Divider(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .background(gradient)
                .constrainAs(topDivider) {
                    top.linkTo(parent.top)

                }, thickness = 1.dp
        )

        //like count
        Text(
            modifier = Modifier.constrainAs(likeCount) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            text = feedItem.reaction?.likeCount.toString() + "k"
        )
        //like icon
        IconButton(
            modifier = Modifier.constrainAs(likeIcon) { start.linkTo(likeCount.end, 2.dp) },
            onClick = { /*TODO*/ }) {
            Icon(
                painterResource(id = R.drawable.ic_thumb),
                contentDescription = "Localized description",
                tint = Color.Unspecified
            )
        }

        val guideline = createGuidelineFromStart(0.5f)
        //comment count
        Text(
            modifier = Modifier.constrainAs(commentCount) {
//                start.linkTo(parent.start)
//                end.linkTo(parent.end, margin = 2.dp)
                end.linkTo(guideline, 2.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            text = feedItem.reaction?.commentCount.toString()
        )
        //comment icon
        IconButton(
            modifier = Modifier.constrainAs(commentIcon) {
//                start.linkTo(parent.start, margin = 2.dp)
//                end.linkTo(parent.end)
                start.linkTo(guideline, 2.dp)
            },
            onClick = { /*TODO*/ }) {
            Icon(
                painterResource(id = R.drawable.ic_comments),
                contentDescription = "Localized description",
                tint = Color.Unspecified
            )
        }

        //share count
        Text(
            modifier = Modifier.constrainAs(shareIcon) {
                end.linkTo(shareCount.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            text = feedItem.reaction?.shareCount.toString() + "k"
        )
        //share icon
        IconButton(
            modifier = Modifier.constrainAs(shareCount) { end.linkTo(parent.end, 16.dp) },
            onClick = { /*TODO*/ }) {
            Icon(
                painterResource(id = R.drawable.ic_share),
                contentDescription = "Localized description",
                tint = Color.Unspecified
            )
        }
        Divider(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .background(gradient)
                .constrainAs(bottomDivider) {
                    bottom.linkTo(parent.bottom)
                }, thickness = 1.dp
        )
    }

}

@Composable
fun ReactionComment(modifier: Modifier = Modifier, commentItem: CommentItem) {

    Row(modifier) {
        Text(commentItem.dateCommentPosted)
        Text(modifier = Modifier.padding(start = 16.dp), text = "Like")
        Text(modifier = Modifier.padding(start = 16.dp), text = "Reply")
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = commentItem.reactionCount.toString()
        )
        Icon(
            modifier = Modifier.padding(start = 4.dp, top = 2.dp, bottom = 2.dp),
            painter = painterResource(id = R.drawable.ic_comment_react),
            contentDescription = "Localized description",
            tint = Color.Unspecified
        )

    }
}