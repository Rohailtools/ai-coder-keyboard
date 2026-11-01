package com.rohail.aicoderkeyboard.agent

import android.content.Context
import android.content.Intent
import android.util.Log

class TermuxExecutor(private val context: Context) {
    fun executeCommand(command: String) {
        val intent = Intent("com.termux.RUN_COMMAND")
        intent.setClassName("com.termux", "com.termux.app.RunCommandService")
        intent.putExtra("com.termux.RUN_COMMAND_PATH", "/data/data/com.termux/files/usr/bin/bash")
        intent.putExtra("com.termux.RUN_COMMAND_ARGUMENTS", arrayOf("-c", command))
        intent.putExtra("com.termux.RUN_COMMAND_WORKDIR", "/data/data/com.termux/files/home")
        intent.putExtra("com.termux.RUN_COMMAND_BACKGROUND", false)
        intent.putExtra("com.termux.RUN_COMMAND_SESSION_ACTION", "0")
        
        try {
            context.startService(intent)
        } catch (e: Exception) {
            Log.e("TermuxExecutor", "Failed to start Termux service", e)
        }
    }
}
