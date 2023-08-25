package com.niyas.trivify.data.remote.responses.questions

import com.google.gson.annotations.SerializedName

data class QuestionsResponse(
    @SerializedName("response_code")
    val responseCode: Int,
    val results: List<Result>
)