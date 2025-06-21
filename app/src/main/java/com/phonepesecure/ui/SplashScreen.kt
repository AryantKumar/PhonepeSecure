package com.phonepesecure.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.phonepesecure.R

@Composable
fun SplashScreen(navController: NavController) {
    var logoVisible by remember { mutableStateOf(false) }
    var titleVisible by remember { mutableStateOf(false) }
    var subtitleVisible by remember { mutableStateOf(false) }
    var developerVisible by remember { mutableStateOf(false) }

    // Sophisticated logo entrance
    val logoScale by animateFloatAsState(
        targetValue = if (logoVisible) 1f else 0.5f,
        animationSpec = tween(
            durationMillis = 1200,
            easing = CubicBezierEasing(0.25f, 0.46f, 0.45f, 0.94f) // Premium easing
        )
    )

    val logoRotation by animateFloatAsState(
        targetValue = if (logoVisible) 0f else -180f,
        animationSpec = tween(
            durationMillis = 1200,
            easing = CubicBezierEasing(0.25f, 0.46f, 0.45f, 0.94f)
        )
    )

    // Elegant fade and slide for text
    val titleOffset by animateIntAsState(
        targetValue = if (titleVisible) 0 else 50,
        animationSpec = tween(
            durationMillis = 800,
            easing = CubicBezierEasing(0.25f, 0.46f, 0.45f, 0.94f)
        )
    )

    val subtitleOffset by animateIntAsState(
        targetValue = if (subtitleVisible) 0 else 30,
        animationSpec = tween(
            durationMillis = 600,
            easing = CubicBezierEasing(0.25f, 0.46f, 0.45f, 0.94f)
        )
    )

    LaunchedEffect(Unit) {
        delay(200)
        logoVisible = true
        delay(800)
        titleVisible = true
        delay(400)
        subtitleVisible = true
        delay(600)
        developerVisible = true
        delay(1000)
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0A0A0A), // Deep black
                        Color(0xFF1A1A1A), // Charcoal
                        Color(0xFF0A0A0A)  // Deep black
                    )
                )
            )
    ) {
        // Subtle ambient lighting effect
        Box(
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.Center)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
                            Color.Transparent
                        ),
                        radius = 200f
                    ),
                    shape = RoundedCornerShape(200.dp)
                )
                .blur(50.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Premium logo container with glassmorphism
            Box(
                contentAlignment = Alignment.Center
            ) {
                // Subtle outer glow
                Surface(
                    shape = RoundedCornerShape(32.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                    modifier = Modifier
                        .size(160.dp)
                        .scale(logoScale)
                        .alpha(if (logoVisible) 1f else 0f)
                        .blur(20.dp)
                ) {}

                // Main logo container with premium styling
                Surface(
                    shape = RoundedCornerShape(28.dp),
                    shadowElevation = 24.dp,
                    color = Color(0xFF1C1C1E), // Premium dark surface
                    modifier = Modifier
                        .size(140.dp)
                        .scale(logoScale)
                        .rotate(logoRotation)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF2C2C2E),
                                        Color(0xFF1C1C1E)
                                    )
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_shield),
                            contentDescription = "PhonePe Secure Logo",
                            modifier = Modifier
                                .size(80.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(56.dp))

            // Premium typography
            AnimatedVisibility(
                visible = titleVisible,
                enter = fadeIn(tween(800))
            ) {
                Text(
                    text = "PhonePe Secure",
                    fontWeight = FontWeight.W300, // Ultra light for premium feel
                    fontSize = 32.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    letterSpacing = 2.sp, // Premium letter spacing
                    modifier = Modifier.offset(y = titleOffset.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Elegant subtitle
            AnimatedVisibility(
                visible = subtitleVisible,
                enter = fadeIn(tween(600))
            ) {
                Text(
                    text = "ENTERPRISE SECURITY",
                    fontWeight = FontWeight.W400,
                    fontSize = 11.sp,
                    color = Color.White.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center,
                    letterSpacing = 3.sp, // Wide letter spacing for premium look
                    modifier = Modifier.offset(y = subtitleOffset.dp)
                )
            }

            Spacer(modifier = Modifier.height(80.dp))

            // Minimalist developer credit
            AnimatedVisibility(
                visible = developerVisible,
                enter = fadeIn(tween(800))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "CRAFTED BY",
                        fontSize = 9.sp,
                        color = Color.White.copy(alpha = 0.4f),
                        fontWeight = FontWeight.W300,
                        letterSpacing = 2.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Aryant Kumar",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.8f),
                        fontWeight = FontWeight.W400,
                        letterSpacing = 1.sp
                    )
                }
            }
        }
    }
}