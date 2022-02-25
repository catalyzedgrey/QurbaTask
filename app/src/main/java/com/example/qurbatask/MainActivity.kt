package com.example.qurbatask

import android.graphics.drawable.GradientDrawable
import android.graphics.fonts.FontStyle
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.qurbatask.data.BottomNavItem
import com.example.qurbatask.data.FeedItem
import com.example.qurbatask.data.Reaction
import com.example.qurbatask.ui.theme.GradientLeft
import com.example.qurbatask.ui.theme.GradientRight
import com.example.qurbatask.ui.theme.QurbaTaskTheme

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
        BodyContent()
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
            .size(48.dp)
            .clip(CircleShape),
        painter = painterResource(id = stringResourceId),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
fun ThoughtsContent() {
    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
    ) {
        RoundedImage(
            stringResourceId = R.drawable.ic_launcher_background
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp),
            shape = RoundedCornerShape(30.dp),
            value = "Share your food experience",
            onValueChange = {/*TODO*/ })
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
                start.linkTo(parent.start, 15.dp)
            },
            stringResourceId = R.drawable.pfp
        )
        //name
        Text(modifier = Modifier.constrainAs(name) {
            start.linkTo(profilePic.end, 5.dp)
        }, text = feedItem.name)

        //badge
        feedItem.badge?.let {
            Text(
                modifier = Modifier.constrainAs(badge) {
                    start.linkTo(profilePic.end, 5.dp)
                    top.linkTo(name.bottom, 1.dp)
                }, text = it,
                style = MaterialTheme.typography.subtitle1
            )
        }

        //date poster
        Text(
            modifier = Modifier.constrainAs(datePosted) {
                top.linkTo(name.bottom, 1.dp)
                if (feedItem.badge == null) {
                    start.linkTo(profilePic.end, 5.dp)
                } else {
                    start.linkTo(badge.end, 5.dp)
                }
            }, text = feedItem.date,
            style = MaterialTheme.typography.subtitle1
        )

        //more button

        IconButton(
            modifier = Modifier.constrainAs(moreButton) {
                top.linkTo(profilePic.top)
                bottom.linkTo(profilePic.bottom)
                end.linkTo(parent.end, 15.dp)
            },
            onClick = { /*TODO*/ }) {
            Icon(
                modifier = Modifier.rotate(90f),
                imageVector = Icons.Filled.MoreVert, contentDescription = null,
                tint = MaterialTheme.colors.primary.compositeOver(MaterialTheme.colors.secondary)
            )
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
    if (photos.size != 4) {
        Row(modifier) {
            if (photos.size == 1) {
                Image(
                    painter = painterResource(photos[0]),
                    contentDescription = null,
                )
            } else if (photos.size == 2) {
                Image(
                    painter = painterResource(photos[0]),
                    contentDescription = null,
                )
                Image(
                    painter = painterResource(photos[1]),
                    contentDescription = null,
                )
            } else if (photos.size == 3) {
                Column {
                    Image(
                        painter = painterResource(photos[0]),
                        contentDescription = null,
                    )
                }
                Column {
                    Image(
                        painter = painterResource(photos[1]),
                        contentDescription = null,
                    )
                    Image(
                        painter = painterResource(photos[2]),
                        contentDescription = null,
                    )
                }
            } else if (photos.size == 4) {
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
    } else if (photos.size == 4) {
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


    Column(modifier.padding(bottom = 5.dp)) {
        RoundedImageWithText(feedItem = feedItem)
        Text(
            modifier = Modifier.padding(start = 15.dp, end = 15.dp),
            text = feedItem.postText
        )
        PhotoGrid(modifier.padding(top = 5.dp, bottom = 5.dp), feedItem.imageResourceIdList)
        RoundedImageWithText(feedItem = feedItem)
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(gradient),
            onClick = { /*TODO*/ }) {
            Text(text = "View")

        }
        ReactionMenu(feedItem)
    }
}

@Composable
fun ReactionMenu(feedItem: FeedItem) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {

        val (likeCount, likeIcon, commentCount,
            commentIcon, shareCount, shareIcon,
            topDivider, bottomDivider) = createRefs()
        Divider(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp)
                .constrainAs(topDivider) {
                    top.linkTo(parent.top)

                }, color = Color.Blue, thickness = 1.dp
        )

        //like count
        Text(
            modifier = Modifier.constrainAs(likeCount) {
                start.linkTo(parent.start, 15.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            text = feedItem.reaction.likeCount.toString()
        )
        //like icon
        IconButton(
            modifier = Modifier.constrainAs(likeIcon) { start.linkTo(likeCount.end, 2.dp) },
            onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.ThumbUp, contentDescription = "Localized description")
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
            text = feedItem.reaction.commentCount.toString()
        )
        //comment icon
        IconButton(
            modifier = Modifier.constrainAs(commentIcon) {
//                start.linkTo(parent.start, margin = 2.dp)
//                end.linkTo(parent.end)
                start.linkTo(guideline, 2.dp)
            },
            onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Send, contentDescription = "Localized description")
        }

        //share count
        Text(
            modifier = Modifier.constrainAs(shareIcon) {
                end.linkTo(shareCount.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            text = feedItem.reaction.shareCount.toString()
        )
        //share icon
        IconButton(
            modifier = Modifier.constrainAs(shareCount) { end.linkTo(parent.end, 15.dp) },
            onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Share, contentDescription = "Localized description")
        }
        Divider(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp)
                .constrainAs(bottomDivider) {
                    bottom.linkTo(parent.bottom)
                }, color = Color.Blue, thickness = 1.dp
        )
    }

}

@Composable
fun BottomNavigation() {
    val myIcons = Icons.Rounded

    var selectedItem by remember { mutableStateOf(0) }
    val navItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
        ),
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.PlayArrow
        ),
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Person
        ),
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Person
        ),
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home
        )
    )

    BottomNavigation {
        navItems.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = {
                    Icon(item.icon, contentDescription = null)
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

