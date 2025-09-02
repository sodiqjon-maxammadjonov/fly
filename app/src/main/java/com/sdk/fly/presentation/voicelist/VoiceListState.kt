package com.sdk.fly.presentation.voicelist

import com.sdk.fly.domain.models.Voice

data class VoiceListState(
    val isLoading: Boolean = false,
    val voice: List<Voice> = emptyList(),
    val error: String? = null
)