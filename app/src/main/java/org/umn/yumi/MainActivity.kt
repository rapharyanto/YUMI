
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
                                        // Aksi dapat dihubungkan nanti.
                                    }
                                }
                            }
                        )
                    }

                    "map" -> {
                        // Sementara kembali ke halaman Explore
                        // karena halaman Map belum dihubungkan.
                        ExploreScreen(
                            onNavigate = { destination ->
                                if (destination == "restaurant_detail") {
                                    currentPage = "restaurant_detail"
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
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
