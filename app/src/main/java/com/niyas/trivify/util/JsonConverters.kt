package com.niyas.trivify.util

import com.google.gson.Gson

object JsonConverters {
    fun <A> A.toJson(): String? {
        return Gson().toJson(this)
    }

    fun <A> String.fromJson(type: Class<A>): A {
        return Gson().fromJson(this, type)
    }
}