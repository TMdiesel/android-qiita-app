package com.example.qiitademo.presentation.item_detail

import com.example.qiitademo.domain.model.ItemDetail

data class ItemDetailState(
    val item: ItemDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
