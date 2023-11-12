package com.niyas.trivify.ui.categoryList

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.niyas.trivify.R
import com.niyas.trivify.data.remote.responses.categoryList.TriviaCategory
import com.niyas.trivify.ui.theme.CategoryBoxBackground
import com.niyas.trivify.ui.theme.fontFamily
import com.niyas.trivify.util.JsonConverters.toJson
import com.niyas.trivify.util.Screens.LEVEL_SELECTION_SCREEN
import kotlin.random.Random

@Composable
fun CategoryListScreen(
    navController: NavController,
    userName: String,
    viewModel: CategoryListViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(36.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(18.dp))
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .shadow(4.dp, shape = CircleShape)
                        .background(CategoryBoxBackground, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.side_menu_iv),
                        contentDescription = "Side Menu"
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .shadow(4.dp, shape = CircleShape)
                        .background(CategoryBoxBackground, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.side_menu_iv),
                        contentDescription = "Side Menu"
                    )
                }
                Spacer(modifier = Modifier.width(18.dp))
            }
            Text(
                modifier = Modifier.padding(20.dp),
                text = "Hey $userName, what subject\n" +
                        "do you want improve today ?", fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold
            )
            CategoriesList(navController, viewModel)
        }
    }

}

@Composable
fun CategoriesList(
    navController: NavController,
    viewModel: CategoryListViewModel
) {
    val categories by remember { viewModel.categories }
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val itemCount = if (categories.size % 2 == 0) {
            categories.size / 2
        } else categories.size / 2 + 1
        items(itemCount) {
            CategoriesRow(rowIndex = it, categories = categories, navController = navController)
        }
    }
}

@Composable
fun CategoriesRow(
    rowIndex: Int,
    categories: List<TriviaCategory>,
    navController: NavController
) {
    Column {
        Row(Modifier.padding(start = 12.dp, end = 12.dp)) {
            CategoriesSingleItem(
                category = categories[rowIndex * 2],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            if (categories.size >= (rowIndex * 2) + 2)
                CategoriesSingleItem(
                    category = categories[(rowIndex * 2) + 1],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
            else
                Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}


@Composable
fun CategoriesSingleItem(
    navController: NavController,
    modifier: Modifier,
    category: TriviaCategory
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(1.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .aspectRatio(1f)
            .background(CategoryBoxBackground)
            .background(
                Brush.verticalGradient(
                    listOf(
                        CategoryBoxBackground, Color.White
                    )
                )
            )
            .clickable {
                navController.navigate("$LEVEL_SELECTION_SCREEN/${category.toJson()}")
            }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            category.image?.let { image ->
                Image(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .height(60.dp)
                        .width(60.dp),
                    painter = painterResource(id = image),
                    contentDescription = "${category.name} Image"
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = trimCategoryName(category.name), fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp, textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp)
                )

            }
            val randomProgress = Random.nextFloat() * (1 - 0) + 0
            CustomLinearProgressIndicator(randomProgress)
        }
    }
}


@Composable
fun CustomLinearProgressIndicator(indicatorProgress: Float) {
    var progress by remember { mutableStateOf(0f) }
    val progressAnimDuration = 1500
    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing),
        label = "LinearProgressIndicator"
    )
    LinearProgressIndicator(
        modifier = Modifier
            .clip(CircleShape)
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp),
        progress = progressAnimation
    )
    LaunchedEffect(indicatorProgress) {
        progress = indicatorProgress
    }
}

fun trimCategoryName(categoryName: String): String {
    val stringsToRemove = listOf("Entertainment:", "Science:")
    var updateCategoryName = categoryName
    stringsToRemove.forEach {
        if (categoryName.startsWith(it))
            updateCategoryName = categoryName.removePrefix(it)
    }
    return updateCategoryName
}