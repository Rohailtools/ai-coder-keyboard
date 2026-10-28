package com.rohail.aicoderkeyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.rohail.aicoderkeyboard.llm.GeminiProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AiCoderKeyboardService : InputMethodService() {
    private val scope = CoroutineScope(Dispatchers.Main)
    private val llmProvider = GeminiProvider("DUMMY_KEY")

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
        val ic = currentInputConnection ?: return
        val textBefore = ic.getTextBeforeCursor(100, 0)?.toString() ?: ""
        
        scope.launch {
            val response = llmProvider.generateResponse(textBefore)
            ic.commitText(response, 1)
        }
    }
}
