package org.umn.yumi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.* // Memuat getValue, setValue, mutableStateOf, remember
import org.umn.yumi.ui.theme.YumiTheme

// Kode Warna Sesuai SVG
val BgCream = Color(0xFFFFFBEB)
val PrimaryRed = Color(0xFFA51C1C)
val TextBrown = Color(0xFF59413C)
val BorderColor = Color(0xFFEDE6D5)

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgCream)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // --- HEADER SECTION (MERAH) ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .background(PrimaryRed)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Left Side: Profile Icon & Location Pill
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Avatar Profile Dummy
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray)
                        )

                        // Location Pill
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = BgCream,
                            onClick = { /* Handle lokasi */ }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = "Location",
                                    tint = PrimaryRed,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = "Gading Serpong",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextBrown
                                )
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Dropdown",
                                    tint = PrimaryRed,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }

                    // Right Side: Notification Icon
                    IconButton(onClick = { /* Handle Notifikasi */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notification",
                            tint = BgCream
                        )
                    }
                }
            }
        }

        // --- FLOATING SEARCH BAR ---
        // 1. Tambahkan variabel penampung teks pencarian di bagian atas HomeScreen()
        var searchQuery by remember { mutableStateOf("") }
        // Tambahkan baris ini di bagian variabel warna paling atas:
        val TextPlaceholder = Color(0xFFD0C0B2)
        // 2. Ganti blok Surface Search Bar dengan kode ini:
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.5.dp)
                .border(1.5.dp, BorderColor, RoundedCornerShape(999.dp)),
            shape = RoundedCornerShape(999.dp),
            color = BgCream
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = TextBrown,
                    modifier = Modifier.size(20.dp)
                )

                // Menggunakan TextField transparan agar bisa diketik langsung
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = {
                        Text(
                            text = "Cari sate, kopi susu, ramen...",
                            color = TextPlaceholder,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent, // Hilangkan garis bawah saat diklik
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = PrimaryRed
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    YumiTheme {
        HomeScreen()
    }
}