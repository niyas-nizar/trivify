package com.niyas.trivify.ui.categoryList

import androidx.compose.runtime.Composable
import com.niyas.trivify.data.remote.responses.categoryList.TriviaCategory
import com.niyas.trivify.util.JsonConverters.fromJson
import timber.log.Timber

@Composable
fun QuestionnaireLevelScreen(selectedCategory: String?) {
    Timber.e(selectedCategory?.fromJson(TriviaCategory::class.java).toString())

}