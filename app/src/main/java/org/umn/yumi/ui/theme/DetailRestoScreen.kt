
package org.umn.yumi.ui.theme

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

private val RestoRed = Color(0xFFA83219)
private val RestoBackground = Color(0xFFFCF9FD)
private val RestoLavender = Color(0xFFF0EAF9)
private val RestoText = Color(0xFF25232A)
private val RestoMuted = Color(0xFF817A86)
private val RestoBorder = Color(0xFFE8DDF2)

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
            priceRange = "15k – 45k/orang",
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
            priceRange = "20k – 50k/orang",
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
            priceRange = "25k – 60k/orang",
            distance = "450m dari lokasimu",
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
        containerColor = RestoBackground,
        bottomBar = {
            Surface(
                color = Color.White,
                shadowElevation = 10.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Estimasi Pengantaran",
                            fontSize = 10.sp,
                            color = RestoMuted
                        )
                        Spacer(Modifier.height(3.dp))
                        Text(
                            text = "20–30 Menit",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF358A65)
                        )
                    }

                    Surface(
                        modifier = Modifier
                            .weight(1.25f)
                            .height(46.dp)
                            .clickable { onNavigate("map") },
                        color = RestoRed,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "⌖  Pesan / Buka Rute",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
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
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .clickable { onBack() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "‹",
                            color = RestoText,
                            fontSize = 32.sp
                        )
                    }

                    Spacer(Modifier.width(8.dp))

                    Surface(
                        color = Color(0xFFFFE7DE),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(
                            text = "Y",
                            color = RestoRed,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(
                                horizontal = 6.dp,
                                vertical = 4.dp
                            )
                        )
                    }

                    Spacer(Modifier.width(9.dp))

                    Text(
                        text = "Detail Resto",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = RestoText,
                        modifier = Modifier.weight(1f)
                    )

                    Image(
                        painter = painterResource(R.drawable.avatar_sarah),
                        contentDescription = "Profil pengguna",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 4.dp,
                            bottom = 12.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("⌖", color = RestoRed, fontSize = 18.sp)
                    Spacer(Modifier.width(5.dp))

                    Text(
                        text = resto.location,
                        color = RestoText,
                        fontSize = 11.sp,
                        modifier = Modifier.weight(1f)
                    )

                    RestoCircleAction("•") { onNavigate("share") }
                    Spacer(Modifier.width(8.dp))
                    RestoCircleAction("▮") { onNavigate("bookmark") }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(226.dp)
                        .clip(RoundedCornerShape(15.dp))
                ) {
                    Image(
                        painter = painterResource(resto.image),
                        contentDescription = resto.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Surface(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(10.dp),
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xF5FFFFFF)
                    ) {
                        Text(
                            text = "●  Ramai Lancar",
                            color = Color(0xFF176C52),
                            fontSize = 9.sp,
                            modifier = Modifier.padding(
                                horizontal = 9.dp,
                                vertical = 6.dp
                            )
                        )
                    }

                    Surface(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(10.dp),
                        shape = RoundedCornerShape(7.dp),
                        color = Color(0xCC29252A)
                    ) {
                        Text(
                            text = "▣  Foto restoran",
                            color = Color.White,
                            fontSize = 9.sp,
                            modifier = Modifier.padding(
                                horizontal = 7.dp,
                                vertical = 5.dp
                            )
                        )
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 14.dp
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = resto.name,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            color = RestoText,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )

                        Spacer(Modifier.width(7.dp))

                        Surface(
                            color = Color(0xFFFFE5C3),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text(
                                text = "★ ${resto.rating}",
                                color = Color(0xFF765018),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(
                                    horizontal = 9.dp,
                                    vertical = 5.dp
                                )
                            )
                        }
                    }

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = resto.category,
                        fontSize = 12.sp,
                        color = RestoMuted
                    )

                    Spacer(Modifier.height(7.dp))

                    Text(
                        text = "Rp ${resto.priceRange}",
                        color = RestoRed,
                        fontSize = 11.sp
                    )

                    Spacer(Modifier.height(5.dp))

                    Text(
                        text = "${resto.reviewCount} ulasan terpercaya  •  Lihat ulasan",
                        color = RestoMuted,
                        fontSize = 10.sp,
                        modifier = Modifier.clickable {
                            selectedTab = "Review & Foto"
                        }
                    )

                    Spacer(Modifier.height(13.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(7.dp)
                    ) {
                        RestoStatusChip(
                            text = "● Buka",
                            background = Color(0xFF9DF0D1),
                            foreground = Color(0xFF176C52)
                        )
                        RestoStatusChip(
                            text = "♧ AC Ruangan",
                            background = RestoLavender,
                            foreground = RestoText
                        )
                        RestoStatusChip(
                            text = "P Parkir",
                            background = RestoLavender,
                            foreground = RestoText
                        )
                    }

                    Spacer(Modifier.height(15.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(9.dp)
                    ) {
                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .border(
                                    1.dp,
                                    RestoRed,
                                    RoundedCornerShape(12.dp)
                                )
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { onNavigate("map") },
                            color = RestoRed
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "⌖  Arahkan Rute",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { onNavigate("contact") },
                            color = RestoLavender,
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "♧  Hubungi Resto",
                                    color = RestoText,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 17.dp,
                            bottom = 17.dp
                        )
                        .clip(RoundedCornerShape(13.dp))
                        .background(RestoLavender)
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                    verticalAlignment = Alignment.CenterVertically
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
                            shape = RoundedCornerShape(10.dp),
                            color = if (selected) Color.White else Color.Transparent
                        ) {
                            Box(
                                modifier = Modifier.padding(
                                    horizontal = 3.dp,
                                    vertical = 10.dp
                                ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = tab,
                                    color = if (selected) RestoRed else RestoText,
                                    fontSize = 10.sp,
                                    fontWeight = if (selected) {
                                        FontWeight.SemiBold
                                    } else {
                                        FontWeight.Normal
                                    },
                                    maxLines = 2
                                )
                            }
                        }
                    }
                }
            }

            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Text(
                        text = when (selectedTab) {
                            "Review & Foto" -> "Review Terbaru"
                            "Info Lengkap" -> "Informasi Restoran"
                            else -> "Paling Banyak Dipesan"
                        },
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = RestoText
                    )

                    Spacer(Modifier.height(12.dp))

                    when (selectedTab) {
                        "Menu Populer" -> {
                            RestoMenuCard(resto = resto)
                        }

                        "Review & Foto" -> {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(13.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Text(
                                        text = "★ ${resto.rating}/5 • ${resto.reviewCount} ulasan",
                                        color = RestoRed,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )

                                    Spacer(Modifier.height(7.dp))

                                    Text(
                                        text = "“${resto.review}”",
                                        color = RestoText,
                                        fontSize = 12.sp,
                                        lineHeight = 19.sp
                                    )

                                    Spacer(Modifier.height(7.dp))

                                    Text(
                                        text = "Sarah A. • Pengunjung",
                                        color = RestoMuted,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }

                        else -> {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(13.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Text(
                                        text = resto.name,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = RestoText
                                    )

                                    Spacer(Modifier.height(8.dp))

                                    Text(
                                        text = resto.location,
                                        fontSize = 12.sp,
                                        color = RestoMuted
                                    )

                                    Spacer(Modifier.height(5.dp))

                                    Text(
                                        text = resto.openingHours,
                                        fontSize = 12.sp,
                                        color = RestoMuted
                                    )

                                    Spacer(Modifier.height(5.dp))

                                    Text(
                                        text = resto.category,
                                        fontSize = 12.sp,
                                        color = RestoText
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
private fun RestoCircleAction(
    icon: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(36.dp)
            .clickable(onClick = onClick),
        shape = CircleShape,
        color = RestoLavender
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = icon,
                color = RestoRed,
                fontSize = 18.sp
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
        shape = RoundedCornerShape(18.dp)
    ) {
        Text(
            text = text,
            color = foreground,
            fontSize = 10.sp,
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 8.dp
            ),
            maxLines = 1
        )
    }
}

@Composable
private fun RestoMenuCard(resto: RestoDetailData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(13.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(82.dp)
                    .clip(RoundedCornerShape(10.dp))
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
                        .padding(4.dp),
                    color = RestoRed,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "Favorit",
                        color = Color.White,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            horizontal = 4.dp,
                            vertical = 3.dp
                        )
                    )
                }
            }

            Spacer(Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = resto.menuName,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = RestoText
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = resto.menuDescription,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                    color = RestoMuted,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(5.dp))

                Text(
                    text = resto.menuPrice,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = RestoRed
                )
            }
        }
    }
}
