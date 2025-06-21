package com.phonepesecure.ui.spend

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.phonepesecure.viewmodel.SpendChartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpendChartScreen(
    navController: NavController,
    viewModel: SpendChartViewModel = viewModel()
) {
    val spends by viewModel.dailySpends.collectAsState(initial = emptyList())
    val surfaceColor = MaterialTheme.colorScheme.surface
    val primaryColor = MaterialTheme.colorScheme.primary
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.TrendingUp,
                            contentDescription = null,
                            tint = primaryColor,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Daily Spend Analytics",
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.clip(RoundedCornerShape(12.dp))
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = onSurfaceColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor,
                    titleContentColor = onSurfaceColor
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surface,
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                        )
                    )
                )
        ) {
            // Header Stats Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(8.dp, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            "Total Entries",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "${spends.size}",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Icon(
                        Icons.Default.AccountBalanceWallet,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = primaryColor
                    )
                }
            }

            // Chart Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .shadow(12.dp, RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = surfaceColor
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        "Spending Trend",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = onSurfaceColor,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    AndroidView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(320.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        factory = { context ->
                            LineChart(context).apply {
                                xAxis.position = XAxis.XAxisPosition.BOTTOM
                                xAxis.setDrawGridLines(true)
                                xAxis.gridColor = Color.LTGRAY
                                xAxis.gridLineWidth = 0.5f
                                xAxis.textColor = onSurfaceColor.toArgb()
                                xAxis.textSize = 10f

                                axisLeft.setDrawGridLines(true)
                                axisLeft.gridColor = Color.LTGRAY
                                axisLeft.gridLineWidth = 0.5f
                                axisLeft.textColor = onSurfaceColor.toArgb()
                                axisLeft.textSize = 10f

                                axisRight.isEnabled = false
                                description.isEnabled = false
                                legend.isEnabled = true
                                legend.textColor = onSurfaceColor.toArgb()
                                legend.textSize = 12f

                                setTouchEnabled(true)
                                setPinchZoom(true)
                                isDragEnabled = true
                                setScaleEnabled(true)

                                setBackgroundColor(surfaceColor.toArgb())

                                setExtraOffsets(10f, 20f, 10f, 10f)
                            }
                        },
                        update = { chart ->
                            val entries = spends.mapIndexed { index, spend ->
                                Entry(index.toFloat(), spend.total.toFloat())
                            }

                            val labels = spends.map { it.date }

                            val dataSet = LineDataSet(entries, "Spend Amount (₹)").apply {
                                color = primaryColor.toArgb()
                                setCircleColor(primaryColor.toArgb())
                                setCircleHoleColor(surfaceColor.toArgb()) // ✅ Fixed here
                                lineWidth = 3f
                                circleRadius = 6f
                                circleHoleRadius = 3f
                                valueTextSize = 9f
                                valueTextColor = onSurfaceColor.toArgb()

                                setDrawFilled(true)
                                fillColor = primaryColor.copy(alpha = 0.3f).toArgb()

                                mode = LineDataSet.Mode.CUBIC_BEZIER
                                cubicIntensity = 0.2f
                            }

                            chart.xAxis.valueFormatter = object : ValueFormatter() {
                                override fun getFormattedValue(value: Float): String {
                                    val i = value.toInt()
                                    return if (i in labels.indices) labels[i].substring(5) else ""
                                }
                            }

                            chart.data = LineData(dataSet)
                            chart.animateXY(1000, 1000)
                            chart.invalidate()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
