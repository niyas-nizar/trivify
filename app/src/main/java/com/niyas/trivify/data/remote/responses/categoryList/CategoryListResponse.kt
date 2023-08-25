package com.niyas.trivify.data.remote.responses.categoryList

import com.google.gson.annotations.SerializedName

data class CategoryListResponse(
    @SerializedName("trivia_categories")
    val triviaCategories: List<TriviaCategory>
)