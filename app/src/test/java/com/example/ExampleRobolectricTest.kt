package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Holy Bible", appName)
  }

  @Test
  fun `verify 2 Kings chapters are loaded`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    com.example.data.BibleScriptureStore.loadAmharicAssets(context)

    // Book 12 is 2 Kings (መጽሐፈ ነገሥት ካልዕ)
    val ch1Verses = com.example.data.BibleScriptureStore.getVersesForChapter(12, 1)
    org.junit.Assert.assertTrue("Chapter 1 should have verses", ch1Verses.isNotEmpty())
    org.junit.Assert.assertTrue(
      "Chapter 1 verse 1 text",
      ch1Verses[0].text.contains("አክዓብም ከሞተ በኋላ ሞዓብ በእስራኤል ላይ ዐመፀ")
    )

    val ch14Verses = com.example.data.BibleScriptureStore.getVersesForChapter(12, 14)
    org.junit.Assert.assertTrue("Chapter 14 should have verses", ch14Verses.isNotEmpty())

    val ch18Verses = com.example.data.BibleScriptureStore.getVersesForChapter(12, 18)
    org.junit.Assert.assertTrue("Chapter 18 should have verses", ch18Verses.isNotEmpty())

    val ch25Verses = com.example.data.BibleScriptureStore.getVersesForChapter(12, 25)
    org.junit.Assert.assertTrue("Chapter 25 should have verses", ch25Verses.isNotEmpty())
  }
}
