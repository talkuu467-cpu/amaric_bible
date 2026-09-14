package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.BibleDataProvider
import com.example.data.model.BibleBook
import com.example.data.model.Testament
import com.example.util.GeezUtils

@Composable
fun BibleDrawerContent(
    currentBook: BibleBook,
    onSelectBook: (BibleBook) -> Unit,
    onCloseDrawer: () -> Unit,
    onShowAbout: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var selectedTestament by remember { mutableStateOf(currentBook.testament) }

    val filteredBooks = remember(selectedTestament) {
        BibleDataProvider.books.filter { it.testament == selectedTestament }
    }

    val drawerBg = Color(0xFF181818)
    val itemSelectedBg = Color(0xFF282828)
    val goldColor = Color(0xFFE5A93C)
    val textPrimary = Color(0xFFEEEEEE)

    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(320.dp)
            .background(drawerBg)
            .testTag("bible_drawer_content")
    ) {
        // 1. Header Banner Image with Orthodox Heritage Art & Scripture
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clickable {
                    if (onShowAbout != null) {
                        onCloseDrawer()
                        onShowAbout()
                    }
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_drawer_header),
                contentDescription = "ሀገረ ኢትዮጵያ Header Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Dark gradient overlay for text readability
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.3f),
                                Color.Black.copy(alpha = 0.75f)
                            )
                        )
                    )
            )

            // Header Banner Scripture Text
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ሀገረ  ኢትዮጵያ",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    if (onShowAbout != null) {
                        Surface(
                            color = Color.Black.copy(alpha = 0.6f),
                            shape = RoundedCornerShape(12.dp),
                            border = androidx.compose.foundation.BorderStroke(0.8.dp, goldColor.copy(alpha = 0.8f)),
                            modifier = Modifier.clickable {
                                onCloseDrawer()
                                onShowAbout()
                            }
                        ) {
                            Text(
                                text = "ስለ አዘጋጁ 👤",
                                color = goldColor,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                Column {
                    Text(
                        text = "የላምና የበግ መንጋ በበዛልህ ጊዜ ብርህና ወርቅህም ያለህም ሁሉ በበዛልህ ጊዜ ልብህ እንዳይደነድይ",
                        color = Color.White.copy(alpha = 0.92f),
                        fontSize = 11.5.sp,
                        lineHeight = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "ኦሪት ዘዳግም ምዕራፍ ፰:፲፫ 8:13",
                        color = goldColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // 2. Book List
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            itemsIndexed(filteredBooks, key = { _, b -> b.id }) { index, book ->
                val isSelected = book.id == currentBook.id
                val relativeIndex = index + 1
                val geezNum = GeezUtils.toGeez(relativeIndex)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (isSelected) itemSelectedBg else Color.Transparent)
                        .clickable {
                            onSelectBook(book)
                        }
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = book.amharicName,
                        color = textPrimary,
                        fontSize = 15.5.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        modifier = Modifier.weight(1f)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = geezNum,
                            color = goldColor,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = relativeIndex.toString(),
                            color = goldColor,
                            fontSize = 14.5.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                HorizontalDivider(
                    color = Color(0xFF242424),
                    thickness = 0.6.dp
                )
            }
        }

        // 3. Bottom Tabs: ብሉይ (Old Testament) & ሐዲስ (New Testament)
        Surface(
            color = Color(0xFF141414),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Old Testament Button
                val isOld = selectedTestament == Testament.OLD
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isOld) Color(0xFF383838) else Color.Transparent)
                        .clickable { selectedTestament = Testament.OLD }
                        .padding(horizontal = 8.dp)
                        .testTag("tab_old_testament")
                ) {
                    Text(
                        text = "ብሉይ",
                        color = if (isOld) Color.White else Color(0xFFAAAAAA),
                        fontSize = 16.sp,
                        fontWeight = if (isOld) FontWeight.Bold else FontWeight.Normal
                    )
                }

                // New Testament Button
                val isNew = selectedTestament == Testament.NEW
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isNew) Color(0xFF383838) else Color.Transparent)
                        .clickable { selectedTestament = Testament.NEW }
                        .padding(horizontal = 8.dp)
                        .testTag("tab_new_testament")
                ) {
                    Text(
                        text = "ሐዲስ",
                        color = if (isNew) Color.White else Color(0xFFAAAAAA),
                        fontSize = 16.sp,
                        fontWeight = if (isNew) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
    }
}
