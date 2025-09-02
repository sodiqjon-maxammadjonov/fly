package com.sdk.fly.domain.repository

import com.sdk.fly.domain.models.*

interface VoiceRepository {
    suspend fun getAvailableVoices(language: Language): List<Voice>
    suspend fun generateSpeech(text: String,voiceId: String,language: Language): AudioData
    suspend fun changeVoice(audioData: AudioData,voiceId: String): AudioData
}