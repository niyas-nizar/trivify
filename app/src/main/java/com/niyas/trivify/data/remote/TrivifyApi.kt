package com.niyas.trivify.data.remote

import com.niyas.trivify.data.remote.responses.categoryList.CategoryListResponse
import com.niyas.trivify.data.remote.responses.questions.QuestionsResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TrivifyApi {

    @GET("api_category.php")
    suspend fun getCategories(): CategoryListResponse

    @GET("api.php?amount={count}&category={categoryId}")
    suspend fun getQuestions(
        @Path("count") count: String,
        @Path("categoryId") categoryId: String
    ): QuestionsResponse
}