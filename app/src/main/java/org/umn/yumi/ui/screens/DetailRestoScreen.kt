package org.umn.yumi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.yumi.R

private val RestoRed = Color(0xFFA51C1C)
private val RestoDarkRed = Color(0xFF7D1515)
private val RestoCream = Color(0xFFFFFBEB)
private val RestoText = Color(0xFF3D2923)
private val RestoMuted = Color(0xFF806F63)
private val RestoBorder = Color(0xFFECE3CF)
private val RestoGreen = Color(0xFFE2F1DF)
private val RestoGreenText = Color(0xFF34734B)
private val RestoWhite = Color(0xFFFFFFFF)
private val RestoSoftRed = Color(0xFFF8E8E1)

private data class RestoDetailData(
    val name: String,
    val category: String,
    val image: Int,
    val location: String,
    val rating: String,
    val priceRange: String,
    val distance: String,
    val openingHours: String,
    val menuName: String,
    val menuDescription: String,
    val menuPrice: String,
    val review: String,
    val reviewCount: String
)

private fun getRestoData(restoName: String): RestoDetailData {
    return when {
        restoName.contains("kenangan", ignoreCase = true) -> RestoDetailData(
            name = "Kopi Kenangan",
            category = "Kopi, Roti & Minuman",
            image = R.drawable.kopi_kenangan_food,
            location = "Jakarta Selatan",
            rating = "4.8",
            priceRange = "Rp15.000–45.000/orang",
            distance = "Dekat dari lokasimu",
            openingHours = "Setiap hari • 08.00–22.00",
            menuName = "Kopi Susu Gula Aren",
            menuDescription = "Kopi susu creamy dengan perpaduan gula aren yang pas.",
            menuPrice = "Rp22.000",
            review = "Kopi susunya creamy dan rasa gula arennya pas. Cocok untuk teman nugas!",
            reviewCount = "1.250"
        )

        restoName.contains("nako", ignoreCase = true) -> RestoDetailData(
            name = "Kopi Nako",
            category = "Kopi, Roti & Camilan",
            image = R.drawable.kopi_nako_food,
            location = "Jakarta Selatan",
            rating = "4.7",
            priceRange = "Rp20.000–50.000/orang",
            distance = "Dekat dari lokasimu",
            openingHours = "Setiap hari • 09.00–22.00",
            menuName = "Es Kopi Nako",
            menuDescription = "Kopi dingin yang menyegarkan, cocok ditemani roti panggang.",
            menuPrice = "Rp23.000",
            review = "Kopinya menyegarkan dan tempatnya nyaman buat ngobrol bareng teman.",
            reviewCount = "980"
        )

        else -> RestoDetailData(
            name = "Bakso Solo Samrat",
            category = "Bakso, Aneka Es & Masakan Jawa",
            image = R.drawable.bakso_food,
            location = "Tebet Barat, Jakarta Selatan",
            rating = "4.9",
            priceRange = "Rp25.000–60.000/orang",
            distance = "450 m dari lokasimu",
            openingHours = "Setiap hari • 10.00–22.00",
            menuName = "Bakso Urat Granat Jumbo",
            menuDescription = "Bakso urat sapi dengan tekstur padat dan cita rasa gurih pedas.",
            menuPrice = "Rp35.000",
            review = "Kuah kaldunya gurih banget, baksonya empuk dan porsinya memuaskan!",
            reviewCount = "2.180"
        )
    }
}

@Composable
fun DetailRestoScreen(
    restoName: String = "Bakso Solo Samrat",
    onBack: () -> Unit = {},
    onNavigate: (String) -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf("Menu Populer") }
    val resto = remember(restoName) { getRestoData(restoName) }

    Scaffold(
        containerColor = RestoCream,
        bottomBar = {
            Surface(
                color = RestoWhite,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(horizontal = 18.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Kisaran harga",
                            color = RestoMuted,
                            fontSize = 10.sp
                        )

                        Spacer(Modifier.height(3.dp))

                        Text(
                            text = resto.priceRange.substringBefore("/"),
                            color = RestoText,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Surface(
                        modifier = Modifier
                            .weight(1.2f)
                            .height(46.dp)
                            .clickable { onNavigate("map") },
                        color = RestoRed,
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "⌖",
                                color = RestoWhite,
                                fontSize = 19.sp
                            )

                            Spacer(Modifier.width(7.dp))

                            Text(
                                text = "Buka Rute",
                                color = RestoWhite,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(RestoRed)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier
                            .size(36.dp)
                            .clickable { onBack() },
                        color = Color.White.copy(alpha = 0.14f),
                        shape = CircleShape
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "‹",
                                color = RestoWhite,
                                fontSize = 30.sp
                            )
                        }
                    }

                    Spacer(Modifier.width(11.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "YUMI",
                            color = RestoWhite,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.5.sp
                        )

                        Text(
                            text = "Detail restoran",
                            color = RestoWhite.copy(alpha = 0.8f),
                            fontSize = 11.sp
                        )
                    }

                    RestoHeaderAction(
                        icon = "↗",
                        onClick = { onNavigate("share") }
                    )

                    Spacer(Modifier.width(8.dp))

                    RestoHeaderAction(
                        icon = "♡",
                        onClick = { onNavigate("bookmark") }
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            RestoRed,
                            RoundedCornerShape(
                                bottomStart = 24.dp,
                                bottomEnd = 24.dp
                            )
                        )
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 18.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(226.dp)
                            .clip(RoundedCornerShape(18.dp))
                    ) {
                        Image(
                            painter = painterResource(resto.image),
                            contentDescription = resto.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        Surface(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(10.dp),
                            color = RestoCream,
                            shape = RoundedCornerShape(9.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(
                                    horizontal = 9.dp,
                                    vertical = 6.dp
                                ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    Modifier
                                        .size(7.dp)
                                        .background(
                                            RestoGreenText,
                                            CircleShape
                                        )
                                )

                                Spacer(Modifier.width(5.dp))

                                Text(
                                    text = "Pilihan Foodies",
                                    color = RestoText,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Surface(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(10.dp),
                            color = Color(0xCC3D2923),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "▧  Foto restoran",
                                color = RestoWhite,
                                fontSize = 10.sp,
                                modifier = Modifier.padding(
                                    horizontal = 9.dp,
                                    vertical = 6.dp
                                )
                            )
                        }
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 16.dp
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = resto.name,
                                color = RestoText,
                                fontSize = 22.sp,
                                lineHeight = 27.sp,
                                fontWeight = FontWeight.ExtraBold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(Modifier.height(5.dp))

                            Text(
                                text = resto.category,
                                color = RestoMuted,
                                fontSize = 12.sp
                            )
                        }

                        Spacer(Modifier.width(8.dp))

                        Surface(
                            color = Color(0xFFFFE8B7),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(
                                    horizontal = 11.dp,
                                    vertical = 8.dp
                                ),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "★ ${resto.rating}",
                                    color = Color(0xFF805416),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "Rating",
                                    color = Color(0xFF805416),
                                    fontSize = 9.sp
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(14.dp))

                    RestoInfoLine(
                        icon = "⌖",
                        title = resto.location,
                        subtitle = resto.distance
                    )

                    Spacer(Modifier.height(10.dp))

                    RestoInfoLine(
                        icon = "◷",
                        title = resto.openingHours,
                        subtitle = "Jam operasional"
                    )

                    Spacer(Modifier.height(10.dp))

                    RestoInfoLine(
                        icon = "Rp",
                        title = resto.priceRange,
                        subtitle = "Estimasi biaya per orang"
                    )

                    Spacer(Modifier.height(14.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(7.dp)
                    ) {
                        RestoStatusChip(
                            text = "● Buka",
                            background = RestoGreen,
                            foreground = RestoGreenText
                        )

                        RestoStatusChip(
                            text = "✓ Info restoran",
                            background = RestoWhite,
                            foreground = RestoText
                        )

                        RestoStatusChip(
                            text = "★ Favorit foodies",
                            background = RestoSoftRed,
                            foreground = RestoRed
                        )
                    }

                    Spacer(Modifier.height(17.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .height(45.dp)
                                .clickable { onNavigate("map") },
                            color = RestoRed,
                            shape = RoundedCornerShape(13.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "⌖",
                                    color = RestoWhite,
                                    fontSize = 17.sp
                                )

                                Spacer(Modifier.width(6.dp))

                                Text(
                                    text = "Arahkan Rute",
                                    color = RestoWhite,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .height(45.dp)
                                .border(
                                    1.dp,
                                    RestoBorder,
                                    RoundedCornerShape(13.dp)
                                )
                                .clickable { onNavigate("contact") },
                            color = RestoWhite,
                            shape = RoundedCornerShape(13.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "☎",
                                    color = RestoRed,
                                    fontSize = 15.sp
                                )

                                Spacer(Modifier.width(6.dp))

                                Text(
                                    text = "Hubungi Resto",
                                    color = RestoText,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(9.dp))

                    Text(
                        text = "${resto.reviewCount} ulasan terpercaya  •  Lihat ulasan",
                        color = RestoRed,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable {
                            selectedTab = "Review & Foto"
                        }
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 5.dp
                        )
                        .clip(RoundedCornerShape(14.dp))
                        .background(RestoWhite)
                        .border(
                            1.dp,
                            RestoBorder,
                            RoundedCornerShape(14.dp)
                        )
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    listOf(
                        "Menu Populer",
                        "Review & Foto",
                        "Info Lengkap"
                    ).forEach { tab ->
                        val selected = selectedTab == tab

                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { selectedTab = tab },
                            color = if (selected) RestoRed else Color.Transparent,
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Box(
                                modifier = Modifier.padding(
                                    horizontal = 3.dp,
                                    vertical = 11.dp
                                ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = tab,
                                    color = if (selected) RestoWhite else RestoMuted,
                                    fontSize = 10.sp,
                                    fontWeight = if (selected) {
                                        FontWeight.Bold
                                    } else {
                                        FontWeight.Medium
                                    },
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 15.dp
                    )
                ) {
                    Text(
                        text = when (selectedTab) {
                            "Review & Foto" -> "Cerita dari Foodies"
                            "Info Lengkap" -> "Informasi Restoran"
                            else -> "Menu Favorit Foodies"
                        },
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = RestoText
                    )

                    Spacer(Modifier.height(5.dp))

                    Text(
                        text = when (selectedTab) {
                            "Review & Foto" ->
                                "Pengalaman pengunjung yang bisa jadi referensi."
                            "Info Lengkap" ->
                                "Detail penting sebelum kamu berkunjung."
                            else ->
                                "Rekomendasi menu yang layak kamu coba."
                        },
                        fontSize = 11.sp,
                        lineHeight = 16.sp,
                        color = RestoMuted
                    )

                    Spacer(Modifier.height(13.dp))

                    when (selectedTab) {
                        "Menu Populer" -> {
                            RestoMenuCard(resto = resto)
                        }

                        "Review & Foto" -> {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = RestoWhite
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 1.dp
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(
                                                R.drawable.avatar_sarah
                                            ),
                                            contentDescription = "Sarah A.",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(38.dp)
                                                .clip(CircleShape)
                                        )

                                        Spacer(Modifier.width(10.dp))

                                        Column(
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Text(
                                                text = "Sarah A.",
                                                color = RestoText,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold
                                            )

                                            Text(
                                                text = "Pengunjung • Foodies",
                                                color = RestoMuted,
                                                fontSize = 10.sp
                                            )
                                        }

                                        Text(
                                            text = "★ ${resto.rating}",
                                            color = Color(0xFF805416),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }

                                    Spacer(Modifier.height(12.dp))

                                    Text(
                                        text = "“${resto.review}”",
                                        color = RestoText,
                                        fontSize = 13.sp,
                                        lineHeight = 21.sp
                                    )

                                    Spacer(Modifier.height(10.dp))

                                    Text(
                                        text = "Membantu  •  ${resto.reviewCount} ulasan",
                                        color = RestoMuted,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }

                        else -> {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = RestoWhite
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 1.dp
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    RestoInfoLine(
                                        icon = "⌖",
                                        title = resto.name,
                                        subtitle = resto.location
                                    )

                                    Spacer(Modifier.height(15.dp))

                                    RestoInfoLine(
                                        icon = "◷",
                                        title = "Jam operasional",
                                        subtitle = resto.openingHours
                                    )

                                    Spacer(Modifier.height(15.dp))

                                    RestoInfoLine(
                                        icon = "Rp",
                                        title = "Kisaran harga",
                                        subtitle = resto.priceRange
                                    )

                                    Spacer(Modifier.height(15.dp))

                                    RestoInfoLine(
                                        icon = "★",
                                        title = "Rating ${resto.rating}/5",
                                        subtitle = "${resto.reviewCount} ulasan"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RestoHeaderAction(
    icon: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(34.dp)
            .clickable(onClick = onClick),
        color = Color.White.copy(alpha = 0.14f),
        shape = CircleShape
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = icon,
                color = RestoWhite,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
private fun RestoInfoLine(
    icon: String,
    title: String,
    subtitle: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(36.dp),
            color = RestoSoftRed,
            shape = RoundedCornerShape(11.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = icon,
                    color = RestoRed,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(Modifier.width(10.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = RestoText,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(2.dp))

            Text(
                text = subtitle,
                color = RestoMuted,
                fontSize = 10.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun RestoStatusChip(
    text: String,
    background: Color,
    foreground: Color
) {
    Surface(
        color = background,
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = text,
            color = foreground,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 7.dp
            ),
            maxLines = 1
        )
    }
}

@Composable
private fun RestoMenuCard(
    resto: RestoDetailData
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = RestoWhite
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(11.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(width = 104.dp, height = 112.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Image(
                    painter = painterResource(resto.image),
                    contentDescription = resto.menuName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Surface(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(6.dp),
                    color = RestoRed,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "Favorit",
                        color = RestoWhite,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            horizontal = 7.dp,
                            vertical = 4.dp
                        )
                    )
                }
            }

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = resto.menuName,
                    fontSize = 14.sp,
                    lineHeight = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = RestoText
                )

                Spacer(Modifier.height(5.dp))

                Text(
                    text = resto.menuDescription,
                    fontSize = 11.sp,
                    lineHeight = 16.sp,
                    color = RestoMuted,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(9.dp))

                Text(
                    text = resto.menuPrice,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = RestoRed
                )
            }
        }
    }
}
