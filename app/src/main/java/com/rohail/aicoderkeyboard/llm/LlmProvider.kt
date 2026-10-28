package com.rohail.aicoderkeyboard.llm

interface LlmProvider {
    suspend fun generateResponse(prompt: String): String
}
