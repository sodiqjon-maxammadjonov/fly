package com.sdk.fly.presentation.voicechanger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.fly.domain.models.AudioData
import com.sdk.fly.domain.models.Language
import com.sdk.fly.domain.usecase.ChangeVoice
import com.sdk.fly.domain.usecase.GenerateSpeech
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoiceChangerViewModel @Inject constructor(
    private val generateSpeech: GenerateSpeech,
    private val changeVoice: ChangeVoice
) : ViewModel() {

    private val _state = MutableStateFlow(VoiceChangerState())
    val state: StateFlow<VoiceChangerState> = _state

    fun generateFromText(text: String, voiceId: String, language: Language) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val audio = generateSpeech(text, voiceId, language)
                _state.value = VoiceChangerState(result = audio)
            } catch (e: Exception) {
                _state.value = VoiceChangerState(error = e.message)
            }
        }
    }

    fun changeAudioVoice(audio: AudioData, voiceId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val audioRes = changeVoice(audio, voiceId)
                _state.value = VoiceChangerState(result = audioRes)
            } catch (e: Exception) {
                _state.value = VoiceChangerState(error = e.message)
            }
        }
    }
}
