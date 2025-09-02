package com.sdk.fly.presentation.voicechanger

import com.sdk.fly.domain.models.AudioData

data class VoiceChangerState (
    val isLoading: Boolean = false,
    val result: AudioData? = null,
    val error: String? = null
)