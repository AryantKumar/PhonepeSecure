package com.phonepesecure.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.phonepesecure.model.FraudAlert
import java.util.*
import java.util.regex.Pattern

class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("SmsReceiver", "🔔 SMS RECEIVED")

        val bundle: Bundle? = intent.extras
        val pdus = bundle?.get("pdus") as? Array<*>
        val format = bundle?.getString("format")

        if (pdus != null) {
            for (pdu in pdus) {
                val sms = SmsMessage.createFromPdu(pdu as ByteArray, format)
                val body = sms.messageBody ?: continue

                Log.d("SmsReceiver", "📩 Message: $body")

                val amount = extractAmount(body)
                if (amount > 50000) {
                    Log.d("SmsReceiver", "⚠️ FRAUD DETECTED: ₹$amount")
                    sendFraudAlert(body)
                }
            }
        }
    }

    private fun extractAmount(text: String): Int {
        val pattern = Pattern.compile("₹\\s?([0-9,]+)")
        val matcher = pattern.matcher(text)
        return if (matcher.find()) {
            matcher.group(1)?.replace(",", "")?.toIntOrNull() ?: 0
        } else 0
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
                Log.d("SmsReceiver", "✅ Alert sent to Firebase")
            }
            .addOnFailureListener {
                Log.e("SmsReceiver", "❌ Failed to send alert", it)
            }
    }
}
