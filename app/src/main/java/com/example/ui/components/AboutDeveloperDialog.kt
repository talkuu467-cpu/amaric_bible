package com.example.ui.components

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.FileProvider
import com.example.R
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

@Composable
fun AboutDeveloperDialog(
    onDismissRequest: () -> Unit
) {
    val context = LocalContext.current
    var selectedLangTab by remember { mutableIntStateOf(0) } // 0 = አማርኛ, 1 = English

    val goldColor = Color(0xFFE5A93C)
    val cardBg = Color(0xFF1E1E1E)
    val dialogBg = Color(0xFF141414)
    val borderCol = Color(0xFF333333)

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = dialogBg),
            border = androidx.compose.foundation.BorderStroke(1.2.dp, goldColor.copy(alpha = 0.5f)),
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .padding(vertical = 20.dp)
                .testTag("about_developer_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Header Row with Close Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ስለ አዘጋጁ (About Developer)",
                        color = goldColor,
                        fontSize = 17.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(
                        onClick = onDismissRequest,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Permanent Developer Portrait Photo Card
                Card(
                    shape = RoundedCornerShape(14.dp),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, goldColor.copy(alpha = 0.7f)),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF111111)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                        .clip(RoundedCornerShape(14.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_developer),
                        contentDescription = "ዲያቆን ተክሉ ሚንዳ (ተክሊሽ) - አዘጋጅ",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Photo Action Buttons: Download & Share Photo
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // 1. Download Photo Button
                    OutlinedButton(
                        onClick = {
                            saveDeveloperPhotoToGallery(context)
                        },
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, goldColor.copy(alpha = 0.8f)),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color(0xFF221D12),
                            contentColor = goldColor
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = "Download Photo",
                            modifier = Modifier.size(17.dp),
                            tint = goldColor
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "ፎቶ አውርድ",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    // 2. Share Photo Button
                    OutlinedButton(
                        onClick = {
                            shareDeveloperPhoto(context)
                        },
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF444444)),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color(0xFF1E1E1E),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share Photo",
                            modifier = Modifier.size(17.dp),
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "ፎቶ አጋራ",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Developer Name & Title
                Text(
                    text = "ዲያቆን ተክሉ ሚንዳ (ተክሊሽ)",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Deacon Takluu Minda (Teklish)",
                    color = Color(0xFFCCCCCC),
                    fontSize = 14.5.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Profession Badge
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF2A2415))
                        .border(1.dp, goldColor.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Code,
                        contentDescription = null,
                        tint = goldColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Vib Coder • የቤተክርስቲያን አገልጋይ",
                        color = goldColor,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Language Switch Tabs
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF222222))
                        .padding(3.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (selectedLangTab == 0) goldColor else Color.Transparent)
                            .clickable { selectedLangTab = 0 }
                    ) {
                        Text(
                            text = "አማርኛ",
                            color = if (selectedLangTab == 0) Color.Black else Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (selectedLangTab == 1) goldColor else Color.Transparent)
                            .clickable { selectedLangTab = 1 }
                    ) {
                        Text(
                            text = "English",
                            color = if (selectedLangTab == 1) Color.Black else Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Read-Only Biography Card
                Surface(
                    color = cardBg,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, borderCol),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        if (selectedLangTab == 0) {
                            Text(
                                text = "ስለ እኔ",
                                color = goldColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "በቅድሚያ ለዚህ ክብርና አገልግሎት ስላበቃኝ ፈጣሪዬን እጅግ አድርጌ አመሰግናለሁ።",
                                color = Color(0xFFEEEEEE),
                                fontSize = 14.sp,
                                lineHeight = 21.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "እኔ ዲያቆን ተክሉ ሚንዳ (ተክሊሽ) እባላለሁ። በሙያዬ Vib Coder ስሆን፣ ይህንን የቴክኖሎጂ እና የኮዲንግ እውቀቴን ከተሰጠኝ መንፈሳዊ ጥሪና ጸጋ ጋር በማስተሳሰር ቅዱስ በሆነው የወንጌል አገልግሎት ላይ ለማዋል የቆረጥኩ አገልጋይ ነኝ። የቤተክርስቲያንን መንፈሳዊ ተልዕኮ በዘመናዊ እውቀት፣ በታማኝነትና በከፍተኛ የኃላፊነት ስሜት ለመደገፍ ሁልጊዜ ዝግጁ ነኝ።",
                                color = Color(0xFFDDDDDD),
                                fontSize = 14.sp,
                                lineHeight = 21.sp
                            )
                        } else {
                            Text(
                                text = "About Me",
                                color = goldColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "First and foremost, I deeply thank my Creator for granting me this honor and opportunity to serve.",
                                color = Color(0xFFEEEEEE),
                                fontSize = 14.sp,
                                lineHeight = 21.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "My name is Deacon Takluu Minda (Teklish). Professionally, I am a Vib Coder. I am a dedicated servant of the church, committed to combining my technology and coding expertise with my spiritual calling to serve the holy gospel ministry. I am always ready to support the spiritual mission of the church with modern knowledge, integrity, and a high sense of responsibility.",
                                color = Color(0xFFDDDDDD),
                                fontSize = 14.sp,
                                lineHeight = 21.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Contact Details Section
                Text(
                    text = if (selectedLangTab == 0) "የመገናኛ አድራሻዎች፦" else "Contact Information:",
                    color = goldColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Email Card
                Surface(
                    color = cardBg,
                    shape = RoundedCornerShape(10.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, borderCol),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("mailto:talkuu467@gmail.com")
                                putExtra(Intent.EXTRA_SUBJECT, "Holy Bible App - Inquiry")
                            }
                            try {
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Toast.makeText(context, "ኢሜይል: talkuu467@gmail.com", Toast.LENGTH_LONG).show()
                            }
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email",
                            tint = goldColor,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = if (selectedLangTab == 0) "ኢሜይል (Email)" else "Email",
                                color = Color(0xFFAAAAAA),
                                fontSize = 12.sp
                            )
                            Text(
                                text = "talkuu467@gmail.com",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Phone Card
                Surface(
                    color = cardBg,
                    shape = RoundedCornerShape(10.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, borderCol),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:0905258463")
                            }
                            try {
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Toast.makeText(context, "ስልክ: 0905258463", Toast.LENGTH_LONG).show()
                            }
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Phone",
                            tint = goldColor,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = if (selectedLangTab == 0) "የስልክ ቁጥር (Phone)" else "Phone Number",
                                color = Color(0xFFAAAAAA),
                                fontSize = 12.sp
                            )
                            Text(
                                text = "0905258463",
                                color = Color.White,
                                fontSize = 14.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = onDismissRequest,
                    colors = ButtonDefaults.buttonColors(containerColor = goldColor),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (selectedLangTab == 0) "እሺ / ዝጋ" else "Close",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

private fun saveDeveloperPhotoToGallery(context: Context) {
    try {
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.profile_developer)
        val filename = "Deacon_Takluu_Minda_${System.currentTimeMillis()}.jpg"
        val fos: OutputStream?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/HolyBible")
            }
            val imageUri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            fos = imageUri?.let { context.contentResolver.openOutputStream(it) }
        } else {
            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }
        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
        Toast.makeText(context, "ፎቶው ወደ ስልክዎ ጋለሪ ተቀምጧል! (Photo Saved to Gallery)", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        Toast.makeText(context, "ማስቀመጥ አልተቻለም: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

private fun shareDeveloperPhoto(context: Context) {
    try {
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.profile_developer)
        val cachePath = File(context.cacheDir, "images")
        cachePath.mkdirs()
        val file = File(cachePath, "deacon_takluu_minda.jpg")
        val stream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        stream.close()

        val contentUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            setDataAndType(contentUri, context.contentResolver.getType(contentUri))
            putExtra(Intent.EXTRA_STREAM, contentUri)
            putExtra(
                Intent.EXTRA_TEXT,
                "ዲያቆን ተክሉ ሚንዳ (ተክሊሽ) - የቅዱስ መጽሐፍ መተግበሪያ አዘጋጅ (Vib Coder)\nEmail: talkuu467@gmail.com | Phone: 0905258463"
            )
            type = "image/jpeg"
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share Developer Photo"))
    } catch (e: Exception) {
        val textIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "ዲያቆን ተክሉ ሚንዳ (ተክሊሽ) - የቅዱስ መጽሐፍ መተግበሪያ አዘጋጅ (Vib Coder)\nEmail: talkuu467@gmail.com | Phone: 0905258463"
            )
            type = "text/plain"
        }
        context.startActivity(Intent.createChooser(textIntent, "Share Info"))
    }
}
