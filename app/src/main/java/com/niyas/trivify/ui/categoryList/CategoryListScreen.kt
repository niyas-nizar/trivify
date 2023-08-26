package com.niyas.trivify.ui.categoryList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.niyas.trivify.data.remote.responses.categoryList.TriviaCategory
import com.niyas.trivify.ui.theme.fontFamily
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.niyas.trivify.R
import com.niyas.trivify.ui.theme.CategoryBoxBackground

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
            /*.background(Brush.verticalGradient(
                listOf(Color("FEFDFF"), Color("#FFFFFF")
            ))*/
            .clickable {
                navController.navigate("questionnaire_screen/${category.id}")
            }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            category.image?.let { image ->
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    painter = painterResource(id = image),
                    contentDescription = "${category.name} Image"
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = trimCategoryName(category.name), fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp, textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp, start = 24.dp, end = 24.dp)
            )
        }
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