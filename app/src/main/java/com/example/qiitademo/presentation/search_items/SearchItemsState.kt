package com.example.qiitademo.presentation.search_items

import com.example.qiitademo.domain.model.Item

data class SearchItemsState(
    val items: List<Item> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
