package org.umn.yumi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.umn.yumi.ui.screens.LoginScreen
import org.umn.yumi.ui.screens.OpeningScreen
import org.umn.yumi.ui.theme.YumiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YumiTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "opening"
    ) {
        // 1. Halaman Opening
        composable("opening") {
            OpeningScreen(
                onMulaiEksplorClick = {
                    navController.navigate("login")
                }
            )
        }

        // 2. Halaman Login
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("opening") { inclusive = true }
                    }
                },
                onRegisterClick = {
                    // Nanti dihubungkan ke Register.kt
                },
                onForgotPasswordClick = {
                    // Nanti dihubungkan ke ForgotPassword.kt
                },
                onGuestClick = {
                    navController.navigate("home")
                }
            )
        }

        // 3. Halaman Home (Placeholder sementara sampai Home.kt selesai dibuat)
        composable("home") {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFDF8FF)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🎉 Login Berhasil!\n\n(Tampilan Home.kt sedang dikembangkan)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1C1A28),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppNavigationPreview() {
    YumiTheme {
        AppNavigation()
    }
}