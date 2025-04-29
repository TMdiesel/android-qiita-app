package com.example.qiitademo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.qiitademo.presentation.item_detail.ItemDetailScreen
import com.example.qiitademo.presentation.search_items.SearchItemsScreen
import com.example.qiitademo.presentation.ui.theme.QiitaDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            QiitaDemoTheme {
                val navController = rememberNavController()

                Surface(color = MaterialTheme.colorScheme.background) {
                    NavHost(
                        navController = navController,
                        startDestination = ScreenRoute.SearchItemsScreen.route
                    ) {
                        // 検索画面
                        composable(ScreenRoute.SearchItemsScreen.route) {
                            SearchItemsScreen(navController = navController)
                        }
                        // 詳細画面 ― SavedStateHandle で itemId を受け取るので引数は不要
                        composable(ScreenRoute.ItemDetailScreen.route + "/{itemId}") {
                            ItemDetailScreen()
                        }
                    }
                }
            }
        }
    }
}