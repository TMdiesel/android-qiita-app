package com.example.qiitademo.presentation.item_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qiitademo.common.NetworkResponse
import com.example.qiitademo.domain.usecase.GetItemDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel @Inject constructor(
    private val getItemDetailUseCase: GetItemDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(ItemDetailState(isLoading = true))
    val state: StateFlow<ItemDetailState> = _state.asStateFlow()

    init {
        /** NavHost の引数 `{itemId}` が自動で入る */
        savedStateHandle.get<String>("itemId")?.let { itemId -> loadItemDetail(itemId) }
    }

    private fun loadItemDetail(id: String) = viewModelScope.launch {
        getItemDetailUseCase(id).collect { response ->
            when (response) {
                is NetworkResponse.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }

                is NetworkResponse.Success -> {
                    response.data?.let { item ->
                        _state.update { it.copy(item = item, isLoading = false, error = null) }
                    } ?: run {
                        _state.update { it.copy(isLoading = false, error = "No data received") }
                    }
                }

                is NetworkResponse.Failure -> {
                    _state.update { it.copy(isLoading = false, error = response.error) }
                }
            }
        }
    }
}