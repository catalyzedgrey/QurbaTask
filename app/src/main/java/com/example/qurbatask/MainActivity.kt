package com.example.qurbatask

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.qurbatask.components.FeedListItem
import com.example.qurbatask.components.RoundedImage
import com.example.qurbatask.data.*
import com.example.qurbatask.ui.theme.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        /*
        Splash Screen icon doesnt show on Android 12 but shows on earlier android version
        and only shows the background color chosen
        this might be because of the library is still in beta
        https://stackoverflow.com/questions/69812590/android-12-splash-screen-icon-not-displaying
         */
        installSplashScreen()
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

    //Initialize mock data
    //region mock data
    val feedItem = FeedItem(
        profilePic = R.drawable.chicken_chester,
        name = "Chicken Chester",
        badge = null,
        badgeIcon = R.drawable.ic_badge1,
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
        badgeIcon = R.drawable.ic_badge2,
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
        badgeIcon = null,
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
            badgeIcon = R.drawable.ic_badge2,
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
    //endregion
    val feedItems = listOf<FeedItem>(feedItem, feedItem1, feedItem2)

    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
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
fun Status() {
    var text by remember { mutableStateOf("Share your food experience") }
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 13.dp, bottom = 13.dp)
            .fillMaxWidth()
    ) {
        RoundedImage(stringResourceId = R.drawable.pic1)

        BasicTextField(
            modifier = Modifier
                .padding(start = 12.dp)
                .height(38.dp)
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            value = text,
            cursorBrush = SolidColor(TextColorMuted),
            onValueChange = { text = it },
            textStyle = TextStyle(
                color = TextColorMuted
            ),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .padding(2.dp)
                        .fillMaxWidth()
                        .shadow(1.dp, shape = RoundedCornerShape(20.dp))
                        .padding(1.dp)
                        .background(Color.White, RoundedCornerShape(20.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Box(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 7.dp,
                            bottom = 7.dp
                        )
                    ) {
                        innerTextField()
                    }
                }
            }
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

