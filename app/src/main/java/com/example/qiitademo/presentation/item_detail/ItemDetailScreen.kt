package com.example.qiitademo.presentation.item_detail

import android.graphics.Typeface
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import io.noties.markwon.Markwon
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ItemDetailScreen(
    viewModel: ItemDetailViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    when {
        state.isLoading -> Box(Modifier.fillMaxSize(), Alignment.Center) {
            CircularProgressIndicator()
        }

        state.error != null -> Box(Modifier.fillMaxSize(), Alignment.Center) {
            Text(state.error ?: "Unknown error", color = MaterialTheme.colorScheme.error)
        }

        else -> state.item?.let { item ->
            val markwon = remember(context) { Markwon.create(context) }
            val scrollState = rememberScrollState()
            val textColor = MaterialTheme.colorScheme.onSurface.toArgb()

            val updated = item.updatedAt.toJapaneseDate()
            val created = item.createdAt.toJapaneseDate()

            Surface(color = MaterialTheme.colorScheme.background) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(horizontal = 24.dp, vertical = 32.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    // 👤 著者名
                    Text(
                        text = item.userName ?: "Unknown Author",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                    // 🏷 タイトル
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    )

                    // 🕒 日付表示
                    Text(
                        text = "更新日: $updated　投稿日: $created",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // ❤️ いいね数のみ
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(bottom = 24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "likes",
                            tint = Color.Red,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${item.likesCount ?: 0} likes",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    // 📄 本文
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        AndroidView(
                            factory = { context ->
                                TextView(context).apply {
                                    setTextColor(textColor)
                                    textSize = 16f
                                    typeface = Typeface.MONOSPACE
                                    setLineSpacing(0f, 1.4f)
                                    setPadding(24, 24, 24, 24)
                                }
                            },
                            update = { textView ->
                                markwon.setMarkdown(textView, item.body)
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

private fun String.toJapaneseDate(): String = try {
    OffsetDateTime.parse(this)
        .format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm"))
} catch (e: Exception) {
    this // fallback
}