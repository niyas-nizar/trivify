package com.niyas.trivify.ui.categoryList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niyas.trivify.R
import com.niyas.trivify.data.remote.responses.categoryList.TriviaCategory
import com.niyas.trivify.repository.TrivifyRepository
import com.niyas.trivify.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val repository: TrivifyRepository
) : ViewModel() {

    init {
        getCategories()
    }

    var categories = mutableStateOf<List<TriviaCategory>>(listOf())
    private fun getCategories() {
        viewModelScope.launch {
            when (val result = repository.getCategories()) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    result.data?.triviaCategories?.let {
                        categories.value = injectDrawables(it.toMutableList())
                    }
                }

                is Resource.Error -> {
                }
            }
        }
    }

    private fun injectDrawables(triviaCategories: MutableList<TriviaCategory>): List<TriviaCategory> {
        return triviaCategories.map {
            it.copy(image = R.drawable.side_menu_iv)
        }

    }
}