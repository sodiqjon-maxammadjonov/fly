package com.sdk.fly.domain.repository_impl

import com.sdk.fly.data.remote.api.VoiceApiService
import com.sdk.fly.domain.models.AudioData
import com.sdk.fly.domain.models.Language
import com.sdk.fly.domain.models.Voice
import com.sdk.fly.domain.repository.VoiceRepository

class VoiceRepositoryImpl(
    private val api: VoiceApiService
) : VoiceRepository {
    override suspend fun getAvailableVoices(language: Language): List<Voice> {
        return api.getVoices(language.code).map { it.toDomain() }
    }

    override suspend fun generateSpeech(
        text: String,
        voiceId: String,
        language: Language
    ): AudioData {
        val request = mapOf(
            "text" to text,
            "voiceId" to voiceId,
            "language" to language.code
        )
        return api.generateSpeech(request).toDomain()
    }

    override suspend fun changeVoice(audioData: AudioData, voiceId: String): AudioData {
        val request = mapOf(
            "voiceId" to voiceId,
            "audioBase64" to android.util.Base64.encodeToString(audioData.bytes, android.util.Base64.DEFAULT),
            "format" to audioData.format
        )
        return api.changeVoice(voiceId, request).toDomain()
    }
}
