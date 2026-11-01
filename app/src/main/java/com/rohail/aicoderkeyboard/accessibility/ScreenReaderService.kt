package com.rohail.aicoderkeyboard.accessibility

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.util.Log

class ScreenReaderService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        val node = rootInActiveWindow ?: return
        // TODO: Extract text from node
        Log.d("ScreenReader", "Event detected: ${event?.eventType}")
    }

    override fun onInterrupt() {}
}
