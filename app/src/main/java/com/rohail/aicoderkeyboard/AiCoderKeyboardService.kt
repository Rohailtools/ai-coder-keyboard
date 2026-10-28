package com.rohail.aicoderkeyboard

import android.inputmethodservice.InputMethodService

class AiCoderKeyboardService : InputMethodService() {
    override fun onCreateInputView(): android.view.View {
        return android.widget.Button(this).apply {
            text = "AI Coder Keyboard"
        }
    }
}
