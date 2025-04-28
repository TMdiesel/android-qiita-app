package com.example.qiitademo.presentation

sealed class ScreenRoute(val route: String) {
    object SearchItemsScreen : ScreenRoute("search_items_screen")
    object ItemDetailScreen : ScreenRoute("item_detail_screen")
}