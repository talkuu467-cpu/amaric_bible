package com.example.util

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.Locale

class TextToSpeechHelper(context: Context) {

    private var tts: TextToSpeech? = null
    var isInitialized by mutableStateOf(false)
        private set
    var isSpeaking by mutableStateOf(false)
        private set
    var currentSpeakingVerse by mutableStateOf<Int?>(null)
        private set
    var speechRate by mutableStateOf(1.0f)
        private set

    init {
        tts = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.US
                isInitialized = true
            }
        }

        tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {
                isSpeaking = true
                val verseNum = utteranceId?.toIntOrNull()
                currentSpeakingVerse = verseNum
            }

            override fun onDone(utteranceId: String?) {
                isSpeaking = false
                currentSpeakingVerse = null
            }

            override fun onError(utteranceId: String?) {
                isSpeaking = false
                currentSpeakingVerse = null
            }
        })
    }

    fun speak(text: String, utteranceId: String = "verse_audio") {
        if (!isInitialized) return
        tts?.setSpeechRate(speechRate)
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
        isSpeaking = true
    }

    fun stop() {
        tts?.stop()
        isSpeaking = false
        currentSpeakingVerse = null
    }

    fun setRate(rate: Float) {
        speechRate = rate.coerceIn(0.5f, 2.0f)
        tts?.setSpeechRate(speechRate)
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
        isSpeaking = false
    }
}
