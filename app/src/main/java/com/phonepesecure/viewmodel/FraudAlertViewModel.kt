package com.phonepesecure.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.phonepesecure.model.FraudAlert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.google.firebase.firestore.Query

class FraudAlertViewModel : ViewModel() {

    private val _alerts = MutableStateFlow<List<FraudAlert>>(emptyList())
    val alerts: StateFlow<List<FraudAlert>> = _alerts

    private val firestore = FirebaseFirestore.getInstance()

    init {
        listenForAlerts()
    }

    private fun listenForAlerts() {
        firestore.collection("fraud_alerts")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    error.printStackTrace()
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val alertList = snapshot.documents.mapNotNull { it.toObject(FraudAlert::class.java) }
                    Log.d("FraudAlertViewModel", "Received ${alertList.size} alerts from Firestore")
                    _alerts.value = alertList
                }
            }
    }
}
