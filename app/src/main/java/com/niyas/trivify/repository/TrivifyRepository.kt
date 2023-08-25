package com.niyas.trivify.repository

import com.niyas.trivify.data.remote.TrivifyApi
import com.niyas.trivify.data.remote.responses.categoryList.CategoryListResponse
import com.niyas.trivify.data.remote.responses.questions.QuestionsResponse
import com.niyas.trivify.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class TrivifyRepository @Inject constructor(
    private val api: TrivifyApi
) {
    suspend fun getCategories(): Resource<CategoryListResponse> {
        val response = try {
            api.getCategories()
        } catch (e: Exception) {
            return Resource.Error(message = "Unknown error occurred")
        }
        return Resource.Success(data = response)
    }

    suspend fun getQuestions(count: String, categoryId: String): Resource<QuestionsResponse> {
        val response = try {
            api.getQuestions(count, categoryId)
        } catch (e: Exception) {
            return Resource.Error(message = "Unknown error occurred")
        }
        return Resource.Success(data = response)
    }

}