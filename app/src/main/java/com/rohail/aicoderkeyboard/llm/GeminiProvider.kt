package com.rohail.aicoderkeyboard.llm

class GeminiProvider(private val apiKey: String) : LlmProvider {
    override suspend fun generateResponse(prompt: String): String {
        // TODO: Implement Gemini API call
        return "AI response to: $prompt"
    }
}
