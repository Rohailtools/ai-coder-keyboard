package com.rohail.aicoderkeyboard

import com.rohail.aicoderkeyboard.safety.SafetyManager
import org.junit.Test
import org.junit.Assert.*

class SafetyManagerTest {
    @Test
    fun testDangerousCommandDetection() {
        val manager = SafetyManager()
        assertTrue(manager.isDangerous("rm -rf /"))
        assertFalse(manager.isDangerous("hello world"))
    }
}
