
package org.umn.yumi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.umn.yumi.ui.theme.DetailRestoScreen
import org.umn.yumi.ui.theme.ExploreScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                var currentPage by remember {
                    mutableStateOf("explore")
                }

                var selectedResto by remember {
                    mutableStateOf("Bakso Solo Samrat")
                }

                when (currentPage) {
                    "restaurant_detail" -> {
                        DetailRestoScreen(
                            restoName = selectedResto,
                            onBack = {
                                currentPage = "explore"
                            },
                            onNavigate = { destination ->
                                when (destination) {
                                    "map" -> {
                                        currentPage = "map"
                                    }

                                    "share", "bookmark", "contact" -> {
                                        // Aksi belum diimplementasikan.
                                    }
                                }
                            }
                        )
                    }

                    "map" -> {
                        // Halaman peta masih sementara.
                        ExploreScreen(
                            onNavigate = { destination ->
                                when (destination) {
                                    "restaurant_detail" -> {
                                        currentPage = "restaurant_detail"
                                    }

                                    "Bakso Solo Samrat",
                                    "Kopi Kenangan",
                                    "Kopi Nako" -> {
                                        selectedResto = destination
                                        currentPage = "restaurant_detail"
                                    }

                                    "explore",
                                    "home" -> {
                                        currentPage = "explore"
                                    }
                                }
                            }
                        )
                    }

                    else -> {
                        ExploreScreen(
                            onNavigate = { destination ->
                                when (destination) {
                                    "restaurant_detail" -> {
                                        currentPage = "restaurant_detail"
                                    }

                                    "Bakso Solo Samrat",
                                    "Kopi Kenangan",
                                    "Kopi Nako" -> {
                                        selectedResto = destination
                                        currentPage = "restaurant_detail"
                                    }

                                    "map" -> {
                                        currentPage = "map"
                                    }

                                    "explore",
                                    "home" -> {
                                        currentPage = "explore"
                                    }

                                    "profile",
                                    "voice" -> {
                                        // Halaman/fitur belum tersedia.
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
