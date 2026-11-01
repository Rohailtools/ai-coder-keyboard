package com.rohail.aicoderkeyboard.llm

class MultiModelManager(private val providers: List<LlmProvider>) {
    suspend fun getResponse(prompt: String): String {
        for (provider in providers) {
            try {
                return provider.generateResponse(prompt)
            } catch (e: Exception) {
                // Log and try next
            }
        }
        return "Error: All models failed."
    }
}
