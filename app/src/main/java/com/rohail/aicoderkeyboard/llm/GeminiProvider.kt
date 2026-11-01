package com.rohail.aicoderkeyboard.llm

class GeminiProvider(private val apiKey: String) : LlmProvider {
    override val name = "Gemini"
    override suspend fun generateResponse(prompt: String): String {
        return "Gemini response to: $prompt"
    }
}
