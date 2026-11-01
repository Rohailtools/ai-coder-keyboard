package com.rohail.aicoderkeyboard.safety

import android.text.InputType
import android.view.inputmethod.EditorInfo

class SafetyManager {
    private val dangerousCommands = listOf("rm -rf", "sudo", "git push --force", "chmod -R", "format")

    fun isDangerous(command: String): Boolean = dangerousCommands.any { command.contains(it) }

    fun filterSecrets(text: String): String {
        return text.replace(Regex("(API_KEY|TOKEN|SECRET|PASSWORD)=['\"]?[A-Za-z0-9_-]+['\"]?"), "=***")
    }

    fun isPasswordField(editorInfo: EditorInfo): Boolean {
        val variation = editorInfo.inputType and InputType.TYPE_MASK_VARIATION
        return variation == InputType.TYPE_TEXT_VARIATION_PASSWORD || 
               variation == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD ||
               variation == InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD
    }
}
