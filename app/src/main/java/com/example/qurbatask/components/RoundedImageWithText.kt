package com.example.qurbatask.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.qurbatask.R
import com.example.qurbatask.data.FeedItem
import com.example.qurbatask.ui.theme.TextColor
import com.example.qurbatask.ui.theme.TextColorMuted
import com.example.qurbatask.ui.theme.montserratFamily

@Composable
fun RoundedImageWithText(modifier: Modifier = Modifier, feedItem: FeedItem) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

        val (profilePic, name, badge, badgeIcon,
            datePosted, moreButton) = createRefs()

        //profile pic
        RoundedImage(
            modifier = Modifier.constrainAs(profilePic) {
                start.linkTo(parent.start, 16.dp)
            },
            stringResourceId = feedItem.profilePic
        )
        //name
        Text(
            modifier = Modifier.constrainAs(name) {
                start.linkTo(profilePic.end, 8.dp)
            }, text = feedItem.name,
            style = TextStyle(
                color = TextColor,
                fontSize = 14.sp,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.Bold
            )
        )

        //badge
        feedItem.badge?.let {
            Text(
                modifier = Modifier.constrainAs(badge) {
                    start.linkTo(profilePic.end, 8.dp)
                    top.linkTo(name.bottom, 1.dp)
                }, text = it,
                style = TextStyle(
                    color = TextColorMuted,
                    fontSize = 12.sp,
                )
            )
        }

        //date poster
        Text(
            modifier = Modifier.constrainAs(datePosted) {
                top.linkTo(name.bottom, 1.dp)
                if (feedItem.badge == null) {
                    start.linkTo(profilePic.end, 8.dp)
                } else {
                    start.linkTo(badge.end, 8.dp)
                }
            }, text = feedItem.date,
            style = TextStyle(
                color = TextColorMuted,
                fontSize = 12.sp,
            )
        )
        feedItem.badgeIcon?.let{
            Image(
                painterResource(id = feedItem.badgeIcon) ,
                contentDescription = "badge status",
                modifier = Modifier.constrainAs(badgeIcon){
                    start.linkTo(name.end, 3.dp)
                },)
        }

        IconButton(
            modifier = Modifier.constrainAs(moreButton) {
                top.linkTo(profilePic.top)
                bottom.linkTo(profilePic.bottom)
                end.linkTo(parent.end, 16.dp)
            },
            onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_more),
                null,
                tint = Color.Unspecified
            )
        }
    }
}