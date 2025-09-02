package com.sdk.fly.data.remote.dto

import com.sdk.fly.domain.models.Language
import com.sdk.fly.domain.models.Voice

data class VoiceDto(
    val id : String,
    val name : String,
    val description : String,
    val language : String
) {
    fun toDomain(): Voice{
        return Voice(
            id = id,
            name = name,
            description = description,
            language = Language.valueOf(language.uppercase()),
        )
    }
}