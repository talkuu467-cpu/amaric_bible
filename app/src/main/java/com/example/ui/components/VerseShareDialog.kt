package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.model.BibleVerse

enum class CardStyle(val displayName: String, val bgBrush: Brush, val textColor: Color, val accentColor: Color) {
    ROYAL_GOLD(
        "Warm Gold",
        Brush.linearGradient(listOf(Color(0xFF2C1802), Color(0xFF633A0C))),
        Color(0xFFFFF6EB),
        Color(0xFFFFD54F)
    ),
    DEEP_INDIGO(
        "Deep Indigo",
        Brush.linearGradient(listOf(Color(0xFF0F172A), Color(0xFF1E293B))),
        Color(0xFFF8FAFC),
        Color(0xFF93C5FD)
    ),
    EMERALD_GRACE(
        "Emerald Grace",
        Brush.linearGradient(listOf(Color(0xFF064E3B), Color(0xFF065F46))),
        Color(0xFFECFDF5),
        Color(0xFF6EE7B7)
    ),
    SERENE_PARCHMENT(
        "Parchment",
        Brush.linearGradient(listOf(Color(0xFFFBF7EE), Color(0xFFEFE5D0))),
        Color(0xFF2B2117),
        Color(0xFF8D5B29)
    )
}

@Composable
fun VerseShareDialog(
    verse: BibleVerse,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var selectedStyle by remember { mutableStateOf(CardStyle.ROYAL_GOLD) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("verse_share_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Share Scripture Card",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                // Styled Card Visual Preview
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(selectedStyle.bgBrush)
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FormatQuote,
                            contentDescription = null,
                            tint = selectedStyle.accentColor.copy(alpha = 0.8f),
                            modifier = Modifier.size(32.dp)
                        )

                        Text(
                            text = verse.text,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 17.sp,
                                lineHeight = 26.sp,
                                fontStyle = FontStyle.Italic,
                                fontFamily = FontFamily.Serif
                            ),
                            color = selectedStyle.textColor,
                            textAlign = TextAlign.Center
                        )

                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(2.dp)
                                .background(selectedStyle.accentColor.copy(alpha = 0.5f))
                        )

                        Text(
                            text = "${verse.bookName} ${verse.chapter}:${verse.verse}",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = selectedStyle.accentColor,
                            letterSpacing = 1.sp
                        )

                        Text(
                            text = "HOLY BIBLE",
                            style = MaterialTheme.typography.labelSmall,
                            color = selectedStyle.textColor.copy(alpha = 0.5f),
                            letterSpacing = 2.sp
                        )
                    }
                }

                // Style Selector Circles
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CardStyle.entries.forEach { style ->
                        val isSelected = style == selectedStyle
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(style.bgBrush)
                                .border(
                                    width = if (isSelected) 3.dp else 1.dp,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray.copy(alpha = 0.4f),
                                    shape = CircleShape
                                )
                                .clickable { selectedStyle = style }
                                .testTag("style_option_${style.name}")
                        )
                    }
                }

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("Bible Verse", "\"${verse.text}\" — ${verse.reference} (KJV)")
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, "Copied verse to clipboard", Toast.LENGTH_SHORT).show()
                        },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("copy_share_card_button")
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Copy")
                    }

                    Button(
                        onClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_SUBJECT, "Holy Bible Verse: ${verse.reference}")
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "\"${verse.text}\"\n\n— ${verse.reference} (Holy Bible)"
                                )
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Share Holy Bible Verse"))
                            onDismiss()
                        },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("send_share_intent_button")
                    ) {
                        Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Share")
                    }
                }
            }
        }
    }
}
