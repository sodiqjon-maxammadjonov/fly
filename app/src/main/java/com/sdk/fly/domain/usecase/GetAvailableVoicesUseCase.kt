package com.sdk.fly.domain.usecase

import com.sdk.fly.domain.models.Language
import com.sdk.fly.domain.models.Voice
import com.sdk.fly.domain.repository.VoiceRepository

class GetAvailableVoicesUseCase(
    private val repository: VoiceRepository
) {
    suspend operator fun invoke(language: Language): List<Voice> {
        return repository.getAvailableVoices(language)
    }
}