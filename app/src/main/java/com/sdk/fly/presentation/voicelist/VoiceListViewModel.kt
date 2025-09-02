package com.sdk.fly.presentation.voicelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.fly.domain.models.Language
import com.sdk.fly.domain.usecase.GetAvailableVoices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class VoiceListViewModel @Inject constructor(
    private val getAvailableVoices: GetAvailableVoices
) : ViewModel(){
    private val _state = MutableStateFlow(VoiceListState())
    val state : StateFlow<VoiceListState> = _state
    fun loadVoices (language: Language) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val voices = getAvailableVoices(language)
                _state.value = _state.value.copy(
                    isLoading = false,
                    voice = voices
                )
            } catch (e: Exception){
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}