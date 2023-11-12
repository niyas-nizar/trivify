package com.niyas.trivify.ui.categoryList

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.niyas.trivify.R
import com.niyas.trivify.data.remote.responses.categoryList.TriviaCategory
import com.niyas.trivify.ui.theme.fontFamily
import com.niyas.trivify.util.JsonConverters.fromJson
import com.niyas.trivify.util.JsonConverters.toJson
import com.niyas.trivify.util.Screens.QUESTIONNAIRE_SCREEN
import com.niyas.trivify.util.noRippleClickable

@Composable
fun QuestionnaireLevelScreen(selectedCategory: String, navController: NavController) {
    val category = selectedCategory.fromJson(TriviaCategory::class.java)
    Column {
        Row(modifier = Modifier
            .padding(start = 24.dp, top = 24.dp)
            .noRippleClickable {
                navController.popBackStack()
            }) {
            Image(
                painter = painterResource(id = R.drawable.arrow_left_iv),
                contentDescription = "Back Arrow"
            )
            Text(
                text = "Back", fontFamily = fontFamily, fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp, textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            )
        }
        Text(
            text = category.name, fontFamily = fontFamily, fontWeight = FontWeight.Bold,
            fontSize = 24.sp, textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        )
        Image(
            painter = painterResource(id = category.image ?: 0),
            contentDescription = "${category.name} Image",
            modifier = Modifier
                .padding(start = 36.dp, end = 36.dp)
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
        )
        Text(
            text = "Choose your level", fontFamily = fontFamily, fontWeight = FontWeight.Normal,
            fontSize = 16.sp, textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        Text(
            text = "Easy", fontFamily = fontFamily, fontWeight = FontWeight.Normal,
            fontSize = 16.sp, textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 24.dp)
                .border(shape = CircleShape, width = 2.dp, color = Color.Gray)
                .padding(12.dp)
                .noRippleClickable {
                    navController.navigate("$QUESTIONNAIRE_SCREEN/${category.toJson()}/Easy")
                }
        )
        Text(
            text = "Medium", fontFamily = fontFamily, fontWeight = FontWeight.Normal,
            fontSize = 16.sp, textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 12.dp)
                .border(shape = CircleShape, width = 2.dp, color = Color.Gray)
                .padding(12.dp)
                .noRippleClickable {
                    navController.navigate("$QUESTIONNAIRE_SCREEN/${category.toJson()}/Medium")
                }
        )
        Text(
            text = "Hard", fontFamily = fontFamily, fontWeight = FontWeight.Normal,
            fontSize = 16.sp, textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 12.dp)
                .border(shape = CircleShape, width = 2.dp, color = Color.Gray)
                .padding(12.dp)
                .noRippleClickable {
                    navController.navigate("$QUESTIONNAIRE_SCREEN/${category.toJson()}/Hard")
                }
        )

    }
}