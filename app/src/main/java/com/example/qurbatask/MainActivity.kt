package com.example.qurbatask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.qurbatask.ui.theme.QurbaTaskTheme

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
fun BodyContent() {
    val feedItem0 = FeedItem(
        name = "Chicken Chester",
        badge = "Verified Buyer",
        date = "2 days ago",
        postText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        ArrayList<Int>(),
        32f,
        597,
        12.3f,
        false,
        null
    )
    val feedItem1 = FeedItem(
        name = "Rayna Rosser",
        badge = "Verified Buyer",
        date = "2 days ago",
        postText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        ArrayList<Int>(),
        32f,
        597,
        12.3f,
        false,
        null
    )
    val feedItem2 = FeedItem(
        name = "Skylarani Arcand",
        badge = "Verified Buyer",
        date = "1 sec ago",
        postText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        ArrayList<Int>(),
        32f,
        597,
        12.3f,
        false,
        null
    )

    feedItem0.imageResourceIdList.add(R.drawable.food)
    feedItem0.imageResourceIdList.add(R.drawable.food)
    feedItem0.imageResourceIdList.add(R.drawable.food)
    feedItem0.imageResourceIdList.add(R.drawable.food)
    val feedItems = listOf<FeedItem>(feedItem0, feedItem1, feedItem2, feedItem0)

    Column(modifier = Modifier.fillMaxSize()) {
        ThoughtsContent()
        LazyColumn {
            feedItems.forEach { feedItem ->
                item {
                    FeedListItem(feedItem = feedItem)
                }
            }
        }
    }
}

@Composable
fun ThoughtsContent() {
    Row(modifier = Modifier.padding(10.dp)) {
        Image(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .border(5.dp, Color.Gray, CircleShape),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        OutlinedTextField(
            shape = RoundedCornerShape(30.dp),
            value = "Share your food experience",
            onValueChange = {/*TODO*/ })
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text("ICON") },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Search, contentDescription = "Localized description")
            }
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Notifications, contentDescription = "Localized description")
            }
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.ShoppingCart, contentDescription = "Localized description")
            }
        }
    )
}

@Composable
fun PhotoGrid(photos: List<Int>) {
    if (photos.size != 4) {
        Row {
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
        Column {
            Row {
                Image(
                    painter = painterResource(photos[0]),
                    contentDescription = null,
                )
                Image(
                    painter = painterResource(photos[1]),
                    contentDescription = null,
                )
            }
            Row {
                Image(
                    painter = painterResource(photos[2]),
                    contentDescription = null,
                )
                Image(
                    painter = painterResource(photos[3]),
                    contentDescription = null,
                )
            }
        }
    }
}
@Composable
fun FeedListItem(feedItem: FeedItem) {
    Column {
        RoundedImageWithText()
        Text(text = feedItem.postText)
        PhotoGrid(feedItem.imageResourceIdList)
        RoundedImageWithText()
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            shape = RoundedCornerShape(30.dp),
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
            text = feedItem.likeCount.toString()
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
            text = feedItem.commentCount.toString()
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
            text = feedItem.shareCount.toString()
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
                    top.linkTo(parent.bottom)
                }, color = Color.Blue, thickness = 1.dp
        )
    }

}
@Composable
fun BottomNavigation() {
    var selectedItem by remember { mutableStateOf(0) }

    val map = mapOf<Int, ImageVector>(
        0 to Icons.Filled.Home,
        1 to Icons.Filled.Add,
        2 to Icons.Filled.Person,
        3 to Icons.Filled.Person,
        4 to Icons.Filled.Person
    )
    BottomNavigation {
        for(index  in 0.. 4){
            BottomNavigationItem(
                icon = {
                    val icon: ImageVector = map[index]!!
                    Icon(icon, contentDescription = null)
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