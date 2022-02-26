package com.example.qurbatask.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qurbatask.*
import com.example.qurbatask.data.FeedItem
import com.example.qurbatask.data.RestaurantInfo
import com.example.qurbatask.ui.theme.*

@Composable
fun FeedListItem(modifier: Modifier = Modifier, feedItem: FeedItem) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(
            GradientLeft,
            GradientRight
        )
    )
    Column(Modifier.padding(top = 12.dp)) {
        RoundedImageWithText(feedItem = feedItem)
        Text(
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
            text = feedItem.postText,
            style = TextStyle(
                color = TextColor,
                fontSize = 12.sp,
                fontFamily = montserratFamily
            )
        )
        if (feedItem.hasRetweet) {
            Surface(
                modifier
                    .padding(16.dp)
                    .border(
                        width = 1.dp,
                        color = TextColorMuted, shape = RoundedCornerShape(7.dp)
                    )
            ) {
                FeedListItem(Modifier.padding(top = 8.dp), feedItem = feedItem.feedItem!!)
            }

        }
        PhotoGrid(photos = feedItem.imageResourceIdList)


        feedItem.restaurantInfo?.let {
            ResturantInfo(restaurantInfo = feedItem.restaurantInfo)
        }
        if (feedItem.hasMenuItem) {
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 7.5.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(gradient),
                onClick = { /*TODO*/ }) {
                Text(text = "View Menu")
            }
        }
        feedItem.reaction?.let {
            ReactionMenu(feedItem = feedItem)
        }
        feedItem.comments?.let {
            Comment(commentItem = it[0])
        }
    }
}

@Composable
fun ResturantInfo(
    modifier: Modifier = Modifier,
    restaurantInfo: RestaurantInfo
) {
    Row(
        modifier
            .padding(top = 12.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        RoundedImage(stringResourceId = restaurantInfo.img)
        Column(Modifier.padding(start = 10.dp)) {
            Text(
                restaurantInfo.menuItem,
                style = TextStyle(
                    color = TextColor,
                    fontSize = 14.sp,
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                restaurantInfo.name,
                style = TextStyle(
                    color = TextColorMuted,
                    fontSize = 12.sp
                )
            )
        }
    }
}