package com.sdk.fly.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sdk.fly.domain.models.Language
import com.sdk.fly.presentation.voicechanger.VoiceChangerViewModel
import com.sdk.fly.presentation.voicelist.VoiceListViewModel

@Composable
fun VoiceScreen(
    voiceListViewModel: VoiceListViewModel = hiltViewModel(),
    voiceChangerViewModel: VoiceChangerViewModel = hiltViewModel()
) {
    val voiceListState by voiceListViewModel.state.collectAsState()
    val voiceChangerState by voiceChangerViewModel.state.collectAsState()

    var inputText by remember { mutableStateOf("") }
    var selectedVoice by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        voiceListViewModel.loadVoices(Language.EN)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Voice Changer", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter text") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        if (voiceListState.isLoading) {
            CircularProgressIndicator()
        } else if (voiceListState.error != null) {
            Text("Error: ${voiceListState.error}", color = MaterialTheme.colorScheme.error)
        } else {
            DropdownMenuDemo(
                items = voiceListState.voice.map { it.name },
                onItemSelected = { name ->
                    selectedVoice = voiceListState.voice.first { it.name == name }.id
                }
            )
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                if (inputText.isNotBlank() && selectedVoice.isNotBlank()) {
                    voiceChangerViewModel.generateFromText(
                        text = inputText,
                        voiceId = selectedVoice,
                        language = Language.EN
                    )
                }
            }
        ) {
            Text("Generate Speech")
        }

        Spacer(Modifier.height(16.dp))

        when {
            voiceChangerState.isLoading -> CircularProgressIndicator()
            voiceChangerState.error != null -> Text("Error: ${voiceChangerState.error}", color = MaterialTheme.colorScheme.error)
            voiceChangerState.result != null -> Text("✅ Audio generated (${voiceChangerState.result!!.format})")
        }
    }
}

@Composable
fun DropdownMenuDemo(
    items: List<String>,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(items.firstOrNull() ?: "") }

    Box {
        Button(onClick = { expanded = true }) {
            Text(selectedItem.ifBlank { "Select Voice" })
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEach { label ->
                DropdownMenuItem(
                    text = { Text(label) },
                    onClick = {
                        selectedItem = label
                        expanded = false
                        onItemSelected(label)
                    }
                )
            }
        }
    }
}
