package com.example.qiitademo.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.qiitademo.presentation.search_items.SearchItemsScreen
import com.example.qiitademo.presentation.ui.theme.QiitaDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QiitaDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenRoute.SearchItemsScreen.route,
                    ) {
                        //検索画面
                        composable(route = ScreenRoute.SearchItemsScreen.route) {
                            SearchItemsScreen()
                        }
                        //詳細表示画面
                        composable(route = ScreenRoute.ItemDetailScreen.route + "/{itemId}") {
                            Text(text = "詳細")
                        }
                    }
                }
            }
        }
    }
}
