package org.umn.yumi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.umn.yumi.ui.components.BottomNavigationBar
import org.umn.yumi.ui.profile.ProfileScreen
import org.umn.yumi.ui.theme.YumiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YumiTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    // Menu yang sedang aktif: "beranda", "jelajah", "peta", "profil", "voice_ai"
    var menuAktif by rememberSaveable { mutableStateOf("profil") }

    Scaffold(
        contentWindowInsets = WindowInsets(0),
        bottomBar = {
            BottomNavigationBar(
                selectedMenu = menuAktif,
                onMenuSelected = { menuAktif = it },
                modifier = Modifier.navigationBarsPadding()
            )
        }
    ) { padding ->
        // Isi halaman sesuai menu yang dipilih
        when (menuAktif) {
            "profil" -> ProfileScreen(modifier = Modifier.padding(padding))
            else -> ProfileScreen(modifier = Modifier.padding(padding)) // sementara, sampai layar lain dibuat
        }
    }
}