package com.niyas.trivify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.niyas.trivify.ui.theme.TrivifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrivifyTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "category_list_screen") {
                    composable("category_list_screen") {

                    }
                    composable(
                        "questionnaire_screen/{selected_category}",
                        arguments = listOf(
                            navArgument("selected_category") {
                                type = NavType.StringType
                            })
                    ) {
                        val selectedCategory = remember {
                            it.arguments?.getString("selected_category")
                        }
                    }
                }
            }
        }
    }
}