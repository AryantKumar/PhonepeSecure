package com.phonepesecure.receiver

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.phonepesecure.model.FraudAlert
import java.util.*

class NotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val packageName = sbn.packageName?.lowercase(Locale.ROOT) ?: return
        val extras = sbn.notification.extras
        val title = extras.getString("android.title")?.lowercase(Locale.ROOT) ?: ""
        val text = extras.getCharSequence("android.text")?.toString() ?: ""

        Log.d("NotificationListener", "📲 Package: $packageName")
        Log.d("NotificationListener", "📩 Title: $title")
        Log.d("NotificationListener", "📝 Text: $text")

        // 🔍 Detect potential transaction-like keywords
        if (packageName.contains("sms", true) || title.contains("transaction", true)) {
            val amount = extractAmount(text)
            if (amount > 50000) {
                Log.d("NotificationListener", "⚠️ Suspicious Notification Detected: ₹$amount")
                sendFraudAlert("$title: $text")
            }
        }
    }

    private fun extractAmount(text: String): Int {
        val regex = Regex("₹\\s?([0-9,]+)")
        val match = regex.find(text)
        return match?.groupValues?.get(1)?.replace(",", "")?.toIntOrNull() ?: 0
    }

    private fun sendFraudAlert(message: String) {
        val alert = FraudAlert(
            id = UUID.randomUUID().toString(),
            message = message,
            timestamp = Timestamp.now()
        )

        FirebaseFirestore.getInstance()
            .collection("fraud_alerts")
            .add(alert)
            .addOnSuccessListener {
                Log.d("NotificationListener", "✅ Alert sent to Firebase")
            }
            .addOnFailureListener {
                Log.e("NotificationListener", "❌ Failed to send alert", it)
            }
    }
}
