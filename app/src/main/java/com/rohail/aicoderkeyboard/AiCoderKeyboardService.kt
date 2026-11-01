package com.rohail.aicoderkeyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.rohail.aicoderkeyboard.llm.GeminiProvider
import com.rohail.aicoderkeyboard.safety.SafetyManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AiCoderKeyboardService : InputMethodService() {
    private val scope = CoroutineScope(Dispatchers.Main)
    private val llmProvider = GeminiProvider("DUMMY_KEY")
    private val safetyManager = SafetyManager()

    override fun onCreateInputView(): View {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        val runAgentButton = Button(this).apply {
            text = "Run Agent"
            setOnClickListener {
                runAgent()
            }
        }

        root.addView(runAgentButton)
        return root
    }

    private fun runAgent() {
        if (safetyManager.isPasswordField(currentInputEditorInfo)) {
            Toast.makeText(this, "Agent disabled in password fields", Toast.LENGTH_SHORT).show()
            return
        }

        val ic = currentInputConnection ?: return
        val textBefore = ic.getTextBeforeCursor(100, 0)?.toString() ?: ""
        val safeText = safetyManager.filterSecrets(textBefore)
        
        scope.launch {
            val response = llmProvider.generateResponse(safeText)
            if (safetyManager.isDangerous(response)) {
                Toast.makeText(this@AiCoderKeyboardService, "Dangerous command blocked: $response", Toast.LENGTH_LONG).show()
            } else {
                ic.commitText(response, 1)
            }
        }
    }
}
