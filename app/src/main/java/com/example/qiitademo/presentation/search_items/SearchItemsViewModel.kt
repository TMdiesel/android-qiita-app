package com.example.qiitademo.presentation.search_items

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qiitademo.common.NetworkResponse
import com.example.qiitademo.domain.usecase.SearchItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchItemsViewModel @Inject constructor(
    private val searchItemsUseCase: SearchItemsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchItemsState())
    val state = _state.asStateFlow()

    var query by mutableStateOf("python") // 呼び出し元で変更できるようにしておく

    init {
        searchItems(query)
    }

    fun searchItems(query: String) {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            searchItemsUseCase(query).collect { response ->
                when (response) {
                    is NetworkResponse.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    is NetworkResponse.Success -> {
                        response.data?.let { items ->
                            _state.update {
                                it.copy(
                                    items = items,
                                    isLoading = false,
                                    error = null
                                )
                            }
                        } ?: run {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    error = "No data received"
                                )
                            }
                        }
                    }

                    is NetworkResponse.Failure -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = response.error
                            )
                        }
                    }
                }
            }
        }
    }
}
