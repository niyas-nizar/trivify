package com.niyas.trivify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.niyas.trivify.ui.categoryList.CategoryListScreen
import com.niyas.trivify.ui.categoryList.QuestionnaireLevelScreen
import com.niyas.trivify.ui.theme.TrivifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrivifyTheme(darkTheme = false, dynamicColor = false) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "category_list_screen") {
                    composable("category_list_screen") {
                        CategoryListScreen(navController = navController, userName = "Niyas")
                    }
                    composable(
                        "questionnaire_level_screen/{selected_category}",
                        arguments = listOf(
                            navArgument("selected_category") {
                                type = NavType.StringType
                            })
                    ) {
                        val selectedCategory = remember {
                            it.arguments?.getString("selected_category")
                        }
                        QuestionnaireLevelScreen(selectedCategory)
                    }
                }
            }
        }
    }
}