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
    ThoughtsContent()
    Column(modifier = Modifier.fillMaxSize()) {
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