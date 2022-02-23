package com.example.qurbatask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.qurbatask.ui.theme.QurbaTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QurbaTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
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
        Greeting("Android")
    }
}