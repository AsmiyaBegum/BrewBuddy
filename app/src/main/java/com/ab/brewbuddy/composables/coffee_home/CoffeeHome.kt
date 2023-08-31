package com.ab.brewbuddy.composables.coffee_home

import androidx.activity.ComponentActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.ab.brewbuddy.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeHome() {

    Scaffold { innerPadding ->
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black) // Set the black background color for the top section
            ) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Menu",
                        modifier = Modifier
                            .size(48.dp)
                            .clickable { /* Handle navigation icon click */ }
                    )

                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Menu",
                        modifier = Modifier
                            .size(48.dp)
                            .clickable { /* Handle navigation icon click */ }
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.Black)
            ) {
                Text(
                    text = "Find the best",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(
                    text = "Coffee for you",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                SearchBox()

                Spacer(modifier = Modifier.height(16.dp))

                CoffeeNameList()

                Spacer(modifier = Modifier.height(16.dp))

                CoffeeImageList()

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Special for you",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                SpecialCoffeeList()
            }
        }
    }
}

@Composable
fun SearchBox() {
    val coffeeCupIcon = Icons.Default.AccountBox
    val hintText = "Find your coffee"
    var searchText by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.grey_shade),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 8.dp, horizontal = 18.dp)
        ) {
            Icon(
                imageVector = coffeeCupIcon,
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    textStyle = MaterialTheme.typography.labelSmall.copy(color = Color.White),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {})
                )
                if (searchText.isEmpty()) {
                    Text(
                        text = hintText,
                        color = Color.Gray,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp)) // Add space at the end of the SearchBox
    }
}






@Composable
fun CoffeeNameList() {
    val coffeeNames = listOf("Coffee 1", "Coffee 2", "Coffee 3", "Coffee 4")

    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        coffeeNames.forEach { coffeeName ->
            Text(
                text = coffeeName,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun CoffeeImageList() {
    val coffeeData = listOf(
        CoffeeData(R.drawable.ic_launcher_background, "Coffee 1", "Ingredient 1", "$4.99"),
        CoffeeData(R.drawable.ic_launcher_foreground, "Coffee 2", "Ingredient 2", "$3.49")
    )

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        coffeeData.forEach { coffee ->
            CoffeeCard(coffee)
        }
    }
}

@Composable
fun CoffeeCard(coffee: CoffeeData) {
    Box(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp) // Add start and end padding for the Card
            .fillMaxWidth() // Make the Card stretch to full width
            .background(Color.Black)
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(), // Make the Card content stretch to full width
            shape = RoundedCornerShape(16.dp),
//        elevation = 8.dp
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = coffee.imageResource),
                    contentDescription = "Coffee",
                    modifier = Modifier
                        .fillMaxWidth() // Make the Image stretch to full width
                        .height(120.dp) // Set the height of the Image
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = coffee.name,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = coffee.ingredient,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = coffee.price,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


@Composable
fun SpecialCoffeeList() {
    val specialCoffeeData = listOf(
        CoffeeData(R.drawable.ic_launcher_foreground, "Special Coffee 1", "", ""),
        CoffeeData(R.drawable.ic_launcher_background, "Special Coffee 2", "", "")
    )

    Column {
        specialCoffeeData.forEach { coffee ->
            SpecialCoffeeCard(coffee)
        }
    }
}

@Composable
fun SpecialCoffeeCard(coffee: CoffeeData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Black) // Set the background color here
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
//            elevation = 8.dp
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = coffee.imageResource),
                    contentDescription = "Coffee",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Text(
                    text = coffee.name,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

data class CoffeeData(
    val imageResource: Int,
    val name: String,
    val ingredient: String,
    val price: String
)


@Preview
@Composable
fun CoffeeHomePreview(){
    CoffeeHome()
}