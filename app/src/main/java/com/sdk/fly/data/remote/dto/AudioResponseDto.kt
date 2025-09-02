package com.sdk.fly.data.remote.dto

import com.sdk.fly.domain.models.AudioData

data class AudioResponseDto(
    val audioBase64: String,
    val format: String
) {
    fun toDomain(): AudioData{
        val decoded = android.util.Base64.decode(audioBase64, android.util.Base64.DEFAULT)
        return AudioData(
            bytes = decoded,
            format = format
        )
    }
}