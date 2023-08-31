package com.ab.brewbuddy.home

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ab.brewbuddy.R
import com.ab.brewbuddy.composables.coffee_home.CoffeeHome
import com.ab.brewbuddy.composables.coffee_shop.CoffeeShop
import com.ab.brewbuddy.composables.favourite_coffee.FavouriteCoffee

enum class BottomMenu(val route: String, val label: String, val icon: ImageVector) {
    Home("home", "Home", Icons.Default.Home),
    Shop("shop", "Shop", Icons.Default.List),
    Favorites("favorites", "Favorites", Icons.Default.Favorite),
    Notification("notification", "Profile", Icons.Default.Notifications)
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.background(Color.White),
                backgroundColor = Color.Black,
                elevation = 0.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                BottomMenu.values().forEach { menu ->
                    BottomNavigationItem(
                        icon = { Icon(menu.icon, contentDescription = menu.label) },
                        label = { Text(menu.label) },
                        selected = currentRoute == menu.route,
                        selectedContentColor = colorResource(id = R.color.orange),
                        unselectedContentColor = Color.Gray,
                        onClick = {
                            navController.navigate(menu.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { it
        NavHost(navController, startDestination = BottomMenu.Home.route) {
            composable(BottomMenu.Home.route) { CoffeeHome() }
            composable(BottomMenu.Shop.route) { CoffeeShop() }
            composable(BottomMenu.Favorites.route) { FavouriteCoffee() }
//            composable(BottomMenu.Notification.route) { Notification() }
        }
    }
}

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun HomePage(navController : NavHostController) {
//    var selectedMenu by remember { mutableStateOf(BottomMenu.Home) }
//
//    Scaffold(
//        bottomBar = {
//            BottomNavigation(
//                modifier = Modifier.background(Color.White),
//                backgroundColor = Color.Black,
//                elevation = 0.dp
//            ) {
//                val navBackStackEntry by navController.currentBackStackEntryAsState()
//                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
//                BottomNavigationItem(
//                    selected = selectedMenu == BottomMenu.Home,
//                    onClick = {
//                        navController.navigate("home") {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    },
//                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
////                    label = { Text(text = "Home", fontSize = 12.sp) },
//                    selectedContentColor = colorResource(id = R.color.orange),
//                    unselectedContentColor = Color.Gray
//                )
//                BottomNavigationItem(
//                    selected = selectedMenu == BottomMenu.Search,
//                    onClick = { selectedMenu = BottomMenu.Search },
//                    icon = { Icon(Icons.Filled.List, contentDescription = "Search") },
////                    label = { Text(text = "Search", fontSize = 12.sp) },
//                    selectedContentColor = colorResource(id = R.color.orange),
//                    unselectedContentColor = Color.Gray
//                )
//                BottomNavigationItem(
//                    selected = selectedMenu == BottomMenu.Favorites,
//                    onClick = { selectedMenu = BottomMenu.Favorites },
//                    icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites") },
////                    label = { Text(text = "Favorites", fontSize = 12.sp) },
//                    selectedContentColor = colorResource(id = R.color.orange),
//                    unselectedContentColor = Color.Gray
//                )
//                BottomNavigationItem(
//                    selected = selectedMenu == BottomMenu.Notification,
//                    onClick = { selectedMenu = BottomMenu.Notification },
//                    icon = { Icon(Icons.Filled.Notifications, contentDescription = "Notification") },
////                    label = { Text(text = "Profile", fontSize = 12.sp) },
//                    selectedContentColor = colorResource(id = R.color.orange),
//                    unselectedContentColor = Color.Gray
//                )
//            }
//        }
//    ) {it
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = "Selected Menu: ${selectedMenu.name}", fontSize = 20.sp)
//        }
//    }
//}
//
@Preview
@Composable
fun HomePagePreview() {
    AppNavigation()
}
