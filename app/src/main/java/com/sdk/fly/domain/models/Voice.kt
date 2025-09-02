package com.sdk.fly.domain.models

import org.intellij.lang.annotations.Language

data class Voice(
    val id: String,
    val name: String,
    val description: String,
    val language: Language
)
