package com.sdk.fly.domain.usecase

import com.sdk.fly.domain.models.AudioData
import com.sdk.fly.domain.repository.VoiceRepository

class ChangeVoice (
    private val repository: VoiceRepository
) {
    suspend operator fun invoke(audio: AudioData,newVoice: String): AudioData{
        return repository.changeVoice(audio,newVoice)
    }
}