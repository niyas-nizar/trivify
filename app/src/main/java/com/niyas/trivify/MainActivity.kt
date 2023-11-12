package com.niyas.trivify

import android.os.Bundle
import android.widget.Toast
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
import com.niyas.trivify.util.Params.SELECTED_CATEGORY
import com.niyas.trivify.util.Params.SELECTED_LEVEL
import com.niyas.trivify.util.Params.UserName
import com.niyas.trivify.util.Screens.CATEGORY_LIST_SCREEN
import com.niyas.trivify.util.Screens.LEVEL_SELECTION_SCREEN
import com.niyas.trivify.util.Screens.QUESTIONNAIRE_SCREEN
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrivifyTheme(darkTheme = false, dynamicColor = false) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = CATEGORY_LIST_SCREEN) {
                    composable(CATEGORY_LIST_SCREEN) {
                        CategoryListScreen(navController = navController, userName = UserName)
                    }
                    composable(
                        "$LEVEL_SELECTION_SCREEN/{$SELECTED_CATEGORY}",
                        arguments = listOf(
                            navArgument(SELECTED_CATEGORY) {
                                type = NavType.StringType
                            })
                    ) {
                        val selectedCategory = remember {
                            it.arguments?.getString(SELECTED_CATEGORY)
                        }
                        selectedCategory?.let {
                            QuestionnaireLevelScreen(selectedCategory, navController)
                        }
                    }
                    composable(
                        "$QUESTIONNAIRE_SCREEN/{$SELECTED_CATEGORY}/{$SELECTED_LEVEL}",
                        arguments = listOf(
                            navArgument(SELECTED_CATEGORY) {
                                type = NavType.StringType
                            },
                            navArgument(SELECTED_LEVEL) {
                                type = NavType.StringType
                            })
                    ) {
                        val selectedCategory = remember {
                            it.arguments?.getString(SELECTED_CATEGORY)
                        }
                        val selectedLevel = remember {
                            it.arguments?.getString(SELECTED_LEVEL)
                        }
                        Toast.makeText(
                            this@MainActivity,
                            "$selectedCategory $selectedLevel",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
}