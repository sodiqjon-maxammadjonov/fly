package com.sdk.fly.data.remote.api

import com.sdk.fly.data.remote.dto.AudioResponseDto
import com.sdk.fly.data.remote.dto.VoiceDto
import retrofit2.http.*

interface VoiceApiService {
    @GET("voices")
    suspend fun getVoices(
        @Query("language") language: String
    ): List<VoiceDto>

    @POST("generate-speech")
    suspend fun generateSpeech(
        @Body request: Map<String, String>
    ): AudioResponseDto

    @POST("change-voice/{voiceId}")
    suspend fun changeVoice(
        @Path("voiceId") voiceId: String,
        @Body request: Map<String, Any>
    ): AudioResponseDto
}
