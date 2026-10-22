package org.umn.yumi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.umn.yumi.ui.screens.CreateAccountScreen
import org.umn.yumi.ui.screens.ForgotPasswordScreen
import org.umn.yumi.ui.screens.LoginScreen
import org.umn.yumi.ui.screens.OpeningScreen
import org.umn.yumi.ui.screens.homepage.HomeForYou
import org.umn.yumi.ui.screens.homepage.HomeNearby
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
                    // Pindah ke Home & hapus halaman Login dari backstack
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register")
                },
                onForgotPasswordClick = {
                    navController.navigate("forgot_password")
                },
                onGuestClick = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // 3. Halaman Register (CreateAccount)
        composable("register") {
            CreateAccountScreen(
                onRegisterSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        // 4. Halaman Lupa Kata Sandi (ForgotPassword)
        composable("forgot_password") {
            ForgotPasswordScreen(
                onBackToLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        // 5. Halaman Utama (HomeForYou)
        composable("home") {
            // State untuk menyimpan tab yang sedang aktif ("For You" atau "Nearby")
            var selectedTab by remember { mutableStateOf("For You") }

            if (selectedTab == "Nearby") {
                HomeNearby(
                    selectedTab = selectedTab,
                    onTabSelected = { newTab ->
                        selectedTab = newTab
                    }
                )
            } else {
                HomeForYou(
                    selectedTab = selectedTab,
                    onTabSelected = { newTab ->
                        selectedTab = newTab
                    }
                )
            }
        }

        // 6. Halaman Detail Makanan (Pengiriman Argument)
        composable(
            route = "detail/{foodId}",
            arguments = listOf(
                navArgument("foodId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val foodId = backStackEntry.arguments?.getString("foodId") ?: ""

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFDF8FF)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Detail Makanan ID: $foodId\n(Tampilan DetailFoodScreen)",
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