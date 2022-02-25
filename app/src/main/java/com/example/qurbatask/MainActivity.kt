package com.example.qurbatask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.qurbatask.data.*
import com.example.qurbatask.ui.theme.*

val gradient = Brush.horizontalGradient(
    colors = listOf(
        GradientLeft,
        GradientRight
    )
)

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QurbaTaskTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigation() }
    ) {
        BodyContent(Modifier.padding(it))
    }
}

@Composable
fun BodyContent(modifier: Modifier) {
    val feedItem = FeedItem(
        profilePic = R.drawable.chicken_chester,
        name = "Chicken Chester",
        badge = null,
        date = "2 days ago",
        postText = stringResource(id = R.string.lorem_ipsum_long),
        ArrayList<Int>(),
        Reaction(
            32f,
            597,
            12.3f
        ),
        comments = listOf(
            CommentItem(
                profilePic = R.drawable.pic3,
                username = "Jaxson Schleifer",
                commentMessage = "Lorem ipsum",
                dateCommentPosted = "1h",
                reactionCount = 2
            )
        ),
        hasRetweet = false,
        hasMenuItem = true,
        feedItem = null,
        restaurantInfo = RestaurantInfo(
            img = R.drawable.ic_restaurant_tag,
            menuItem = "Chicken Chester",
            name = "Cafe & Restaurant"
        )
    )
    val feedItem1 = FeedItem(
        profilePic = R.drawable.pic4,
        name = "Rayna Rosser",
        badge = "Verified Buyer",
        date = "2 days ago",
        postText = stringResource(id = R.string.lorem_ipsum_long),
        imageResourceIdList = ArrayList<Int>(),
        reaction = Reaction(
            32f,
            597,
            12.3f
        ),
        comments = null,
        hasRetweet = false,
        feedItem = null,
        restaurantInfo = RestaurantInfo(
            img = R.drawable.mc,
            menuItem = "Chicken MAcdo, Carmel Sandau, Fri...",
            name = "Mcdonald's"
        )
    )
    val feedItem2 = FeedItem(
        profilePic = R.drawable.pic5,
        name = "Skylarani Arcand",
        badge = null,
        date = "1 sec ago",
        postText = stringResource(id = R.string.lorem_ipsum_long),
        ArrayList<Int>(),
        reaction = null,
        comments = null,
        hasRetweet = true,
        feedItem = FeedItem(
            profilePic = R.drawable.pic6,
            name = "Rayna Rhiel Madsen",
            badge = null,
            date = "2 days ago",
            postText = stringResource(id = R.string.lorem_ipsum_long),
            ArrayList<Int>(),
            reaction = null,
            comments = null,
            hasRetweet = false,
            feedItem = null,
            restaurantInfo = RestaurantInfo(
                img = R.drawable.chicken_chester,
                menuItem = "buy 2 Chicken Burger Combo and 2...",
                name = "Chicken Chester"
            )
        ),
        restaurantInfo = null
    )

    feedItem.imageResourceIdList.add(R.drawable.food)
    feedItem1.imageResourceIdList.add(R.drawable.food1)
    feedItem1.imageResourceIdList.add(R.drawable.food2)
    feedItem1.imageResourceIdList.add(R.drawable.food3)
    feedItem2.feedItem?.imageResourceIdList?.add(R.drawable.food)
    val feedItems = listOf<FeedItem>(feedItem, feedItem1, feedItem2)

    LazyColumn() {
        item() {
            Status()
        }
        feedItems.forEach { feedItem ->
            item() {
                FeedListItem(modifier, feedItem = feedItem)
            }
        }
    }
}


@Composable
fun RoundedImage(modifier: Modifier = Modifier, stringResourceId: Int) {
    Image(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape),
        painter = painterResource(id = stringResourceId),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
fun Status() {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 13.dp, bottom = 13.dp)
            .fillMaxWidth()
    ) {
        RoundedImage(stringResourceId = R.drawable.pic1)
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            shape = RoundedCornerShape(30.dp),
            value = "Share your food experience",
            textStyle = TextStyle(
                fontSize = 12.sp,
                color = TextColorMuted
            ),
            onValueChange = {/*TODO*/ }

        )
    }
}

@Composable
fun TopBar() {
    TopAppBar(modifier = Modifier
        .height(56.dp)
        .background(Color.White),
        title = { Text("") },
        navigationIcon = {
            Image(
                modifier = Modifier
                    .padding(start = 17.dp)
                    .size(width = 60.dp, height = 16.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null
            )
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    painterResource(id = R.drawable.ic_search),
                    contentDescription = "Localized description",
                    tint = Color.Unspecified
                )
            }
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    painterResource(id = R.drawable.ic_notification),
                    contentDescription = "Localized description",
                    tint = Color.Unspecified
                )
            }
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    painterResource(id = R.drawable.ic_cart),
                    contentDescription = "Localized description",
                    tint = Color.Unspecified
                )
            }
        }
    )
}

@Composable
fun RoundedImageWithText(modifier: Modifier = Modifier, feedItem: FeedItem) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

        val (profilePic, name, badge,
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

@Composable
fun PhotoGrid(modifier: Modifier = Modifier, photos: List<Int>) {
    when (photos.size) {
        1 -> {
            Image(
                painter = painterResource(photos[0]),
                contentDescription = null,
                modifier = modifier.fillMaxWidth(),
            )
        }
        2 -> {
            Row(modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(photos[0]),
                    contentDescription = null,
                )
                Image(
                    painter = painterResource(photos[1]),
                    contentDescription = null,
                )
            }
        }
        3 -> {
            ConstraintLayout(modifier.fillMaxWidth()) {

                val (img1, img2, img3) = createRefs()
                val guideline = createGuidelineFromStart(0.5f)
                Image(
                    painter = painterResource(photos[0]),
                    contentDescription = null,
                    modifier.constrainAs(img1) {
                        start.linkTo(parent.start)
                        end.linkTo(guideline, 2.dp)
                    }
                )

                Image(
                    painter = painterResource(photos[1]),
                    contentDescription = null,
                    modifier.constrainAs(img2) {
                        start.linkTo(guideline, 2.dp)
                        end.linkTo(parent.end)
                        top.linkTo(img1.top)
                    }
                )
                Image(
                    painter = painterResource(photos[2]),
                    contentDescription = null,
                    modifier.constrainAs(img3) {
                        start.linkTo(guideline, 2.5.dp)
                        end.linkTo(parent.end)
                        bottom.linkTo(img1.bottom)
                    }
                )
            }
        }
        4 -> {
            Column(modifier) {
                Row {
                    Image(
                        modifier = Modifier.weight(1f),
                        painter = painterResource(photos[0]),
                        contentDescription = null,
                    )
                    Image(
                        modifier = Modifier.weight(1f),
                        painter = painterResource(photos[1]),
                        contentDescription = null,
                    )
                }
                Row {
                    Image(
                        modifier = Modifier.weight(1f),
                        painter = painterResource(photos[2]),
                        contentDescription = null,
                    )
                    Image(
                        modifier = Modifier.weight(1f),
                        painter = painterResource(photos[3]),
                        contentDescription = null,
                    )
                }
            }
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

@Composable
fun FeedListItem(modifier: Modifier = Modifier, feedItem: FeedItem) {

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
        //RoundedImageWithText(modifier.padding(5.dp), feedItem = feedItem)

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
fun ReactionMenu(modifier: Modifier = Modifier, feedItem: FeedItem) {
    ConstraintLayout(
        modifier.fillMaxWidth()
    ) {

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


@Composable
fun BottomNavigation(modifier: Modifier = Modifier) {

    var selectedItem by remember { mutableStateOf(0) }
    val navItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = painterResource(id = R.drawable.ic_home),
        ),
        BottomNavItem(
            label = "Home",
            icon = painterResource(id = R.drawable.ic_restaurants)
        ),
        BottomNavItem(
            label = "Offers",
            icon = painterResource(id = R.drawable.ic_discount)
        ),
        BottomNavItem(
            label = "Home",
            icon = painterResource(id = R.drawable.ic_contacts)
        ), BottomNavItem(
            label = "Home",
            icon = painterResource(id = R.drawable.ic_profile)
        )
    )

    BottomNavigation {
        navItems.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        item.icon, contentDescription = null,
                        modifier = Modifier.sizeIn(minWidth = 18.dp),
                        tint = Color.Unspecified
                    )
                },
                label = null,
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QurbaTaskTheme {
        MyApp()
    }
}

