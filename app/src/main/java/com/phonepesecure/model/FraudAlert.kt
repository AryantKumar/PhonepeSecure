package com.phonepesecure.model

import com.google.firebase.Timestamp

data class FraudAlert(
    val id: String = "",
    val message: String = "",
    val timestamp: Timestamp = Timestamp.now()
)
