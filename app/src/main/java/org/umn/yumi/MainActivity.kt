package org.umn.yumi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.launch
import org.umn.yumi.ui.components.BottomNavigationBar
import org.umn.yumi.ui.screens.CreateAccountScreen
import org.umn.yumi.ui.screens.DetailRestoScreen
import org.umn.yumi.ui.screens.ExploreScreen
import org.umn.yumi.ui.screens.ForgotPasswordScreen
import org.umn.yumi.ui.screens.HomeForYou
import org.umn.yumi.ui.screens.HomeNearby
import org.umn.yumi.ui.screens.LoginScreen
import org.umn.yumi.ui.screens.MapLocation
import org.umn.yumi.ui.screens.OpeningScreen
import org.umn.yumi.ui.screens.ProfileScreen
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
    val context = LocalContext.current

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val selectedMenu = when (currentRoute) {
        "home" -> "beranda"
        "explore" -> "jelajah"
        "map_location" -> "peta"
        "profile" -> "profil"
        else -> "beranda"
    }

    val showBottomBar = currentRoute in listOf("home", "explore", "map_location", "profile")

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = "opening",
            modifier = Modifier.fillMaxSize()
        ) {
            composable("opening") {
                OpeningScreen(
                    onMulaiEksplorClick = {
                        navController.navigate("login")
                    }
                )
            }

            composable("login") {
                LoginScreen(
                    onLoginSuccess = {
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

            composable("forgot_password") {
                ForgotPasswordScreen(
                    onBackToLoginClick = {
                        navController.popBackStack()
                    }
                )
            }

            composable("home") {
                var selectedTab by rememberSaveable { mutableStateOf("For You") }
                val listState = rememberLazyListState()
                val coroutineScope = rememberCoroutineScope()

                var bookmarkedRestos by remember { mutableStateOf(setOf<String>()) }

                val onRestoClick: (String) -> Unit = { restoName ->
                    navController.navigate("detail_resto/$restoName")
                }

                val onMapClick: () -> Unit = {
                    navController.navigate("map_location")
                }

                val onShareClick: (String) -> Unit = { restoName ->
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "Yuk, cobain kulineran seru di $restoName lewat aplikasi YUMI!")
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, "Bagikan via")
                    context.startActivity(shareIntent)
                }

                val onBookmarkClick: (String) -> Unit = { restoName ->
                    if (bookmarkedRestos.contains(restoName)) {
                        bookmarkedRestos = bookmarkedRestos - restoName
                        Toast.makeText(context, "$restoName dihapus dari bookmark", Toast.LENGTH_SHORT).show()
                    } else {
                        bookmarkedRestos = bookmarkedRestos + restoName
                        Toast.makeText(context, "Berhasil disimpan ke bookmark!", Toast.LENGTH_SHORT).show()
                    }
                }

                if (selectedTab == "Nearby") {
                    HomeNearby(
                        selectedTab = selectedTab,
                        onTabSelected = { newTab -> selectedTab = newTab },
                        onRestoClick = onRestoClick,
                        onMapClick = onMapClick,
                        onShareClick = onShareClick,
                        onBookmarkClick = onBookmarkClick,
                        bookmarkedItems = bookmarkedRestos
                    )
                } else {
                    HomeForYou(
                        selectedTab = selectedTab,
                        onTabSelected = { newTab -> selectedTab = newTab },
                        onRestoClick = onRestoClick,
                        onSeeAllClick = {
                            coroutineScope.launch {
                                listState.animateScrollToItem(index = 3)
                            }
                        },
                        listState = listState,
                        onShareClick = onShareClick,
                        onBookmarkClick = onBookmarkClick,
                        bookmarkedItems = bookmarkedRestos
                    )
                }
            }

            composable(
                route = "detail_resto/{restoName}",
                arguments = listOf(
                    navArgument("restoName") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val restoName = backStackEntry.arguments?.getString("restoName") ?: "Bakso Solo Samrat"
                DetailRestoScreen(
                    restoName = restoName,
                    onBack = {
                        navController.popBackStack()
                    },
                    onNavigate = { action ->
                        if (action == "map") {
                            navController.navigate("map_location")
                        } else if (action == "share") {
                            val sendIntent = Intent().apply {
                                val action = Intent.ACTION_SEND
                                val extraText = "Cek $restoName di YUMI!"
                                type = "text/plain"
                            }
                            context.startActivity(Intent.createChooser(sendIntent, "Bagikan via"))
                        } else if (action == "bookmark") {
                            Toast.makeText(context, "Berhasil disimpan ke bookmark!", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }

            composable("map_location") {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                ) {
                    MapLocation(
                        onBackClicked = {
                            navController.popBackStack()
                        }
                    )
                }
            }

            composable("explore") {
                ExploreScreen(
                    onNavigate = { route ->
                        when (route) {
                            "home" -> navController.navigate("home")
                            "explore" -> navController.navigate("explore")
                            "map" -> navController.navigate("map_location")
                            "profile" -> navController.navigate("profile")
                            "voice" -> {
                                Toast.makeText(
                                    context,
                                    "Voice AI diaktifkan!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            "location", "notifications" -> {
                                Toast.makeText(
                                    context,
                                    "Fitur ini belum tersedia.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                                if (route.startsWith("detail_resto/")) {
                                    navController.navigate(route)
                                }
                            }
                        }
                    }
                )
            }

            composable("profile") {
                ProfileScreen()
            }
        }

        // Bottom Bar dipasang transparan melayang di posisi paling bawah
        if (showBottomBar) {
            BottomNavigationBar(
                selectedMenu = selectedMenu,
                onMenuSelected = { menu ->
                    val targetRoute = when (menu) {
                        "beranda" -> "home"
                        "jelajah" -> "explore"
                        "peta" -> "map_location"
                        "profil" -> "profile"
                        "voice_ai" -> {
                            Toast.makeText(context, "Voice AI diaktifkan!", Toast.LENGTH_SHORT).show()
                            null
                        }
                        else -> null
                    }

                    if (targetRoute != null && currentRoute != targetRoute) {
                        navController.navigate(targetRoute) {
                            popUpTo("home") {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
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