package com.phonepesecure.model

data class DailySpend(
    val date: String = "",   // e.g., "2025-06-21"
    val total: Double = 0.0  // total spend on that day
)
