package com.phonepesecure.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.phonepesecure.model.DailySpend
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SpendChartViewModel : ViewModel() {

    private val _dailySpends = MutableStateFlow<List<DailySpend>>(emptyList())
    val dailySpends: StateFlow<List<DailySpend>> = _dailySpends

    init {
        fetchSpendingData()
    }

    private fun fetchSpendingData() {
        viewModelScope.launch {
            FirebaseFirestore.getInstance()
                .collection("transactions")
                .get()
                .addOnSuccessListener { result ->
                    val grouped = result.documents.groupBy { doc ->
                        doc.getString("date") ?: "unknown"
                    }

                    val spendList = grouped.mapNotNull { (date, docs) ->
                        val total = docs.sumOf { it.getDouble("amount") ?: 0.0 }
                        if (date != "unknown") DailySpend(date, total) else null
                    }.sortedBy { it.date }

                    _dailySpends.value = spendList
                }
        }
    }
}
