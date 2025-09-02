package com.sdk.fly.domain.usecase

import com.sdk.fly.domain.models.AudioData
import com.sdk.fly.domain.models.Language
import com.sdk.fly.domain.repository.VoiceRepository

class GenerateSpeech (
    private val repository: VoiceRepository
) {
    suspend operator fun invoke(text: String,voiceId: String,language: Language): AudioData{
        return repository.generateSpeech(text,voiceId,language)
    }
}