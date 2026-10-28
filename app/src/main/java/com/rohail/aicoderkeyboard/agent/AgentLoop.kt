package com.rohail.aicoderkeyboard.agent

import com.rohail.aicoderkeyboard.llm.LlmProvider
import org.json.JSONObject

class AgentLoop(private val llmProvider: LlmProvider) {
    suspend fun runStep(screenText: String, task: String): Action {
        val prompt = "Task: $task. Current screen: $screenText. Action as JSON: {\"type\": \"...\", \"value\": \"...\"}"
        val response = llmProvider.generateResponse(prompt)
        val json = JSONObject(response)
        return Action(json.getString("type"), json.optString("value"))
    }
}
