
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.yumi.R

private val ExploreRed = Color(0xFFA83219)
private val ExploreLavender = Color(0xFFF2EAFB)
private val ExploreBackground = Color(0xFFFCF9FD)
private val ExploreText = Color(0xFF25232A)
private val ExploreMuted = Color(0xFF817A86)
private val ExploreGreen = Color(0xFFDDF3E7)
private val ExploreBorder = Color(0xFFE8DDF2)

private data class ExploreReview(
    val name: String,
    val badge: String,
    val level: String,
    val place: String,
    val taste: String,
    val atmosphere: String,
    val price: String,
    val description: String,
    val image: Int,
    val avatar: Int,
    val restoName: String
)

@Composable
fun ExploreScreen(
    onNavigate: (String) -> Unit = {}
) {
    var query by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("Terbaru") }

    val reviews = remember {
        listOf(
            ExploreReview(
                name = "Sarah A.",
                badge = "Top Foodie Jakarta",
                level = "Level 6 Epicure",
                place = "Bakso Solo Samrat, Senopati",
                taste = "9.8/10",
                atmosphere = "8.5/10",
                price = "Nilai Uang",
                description = "Kuah kaldunya rich banget, sumsumnya gurih parah! Sambalnya wajib coba, pedas nagih. Jangan lupa pesan es kelapa mudanya!",
                image = R.drawable.bakso_food,
                avatar = R.drawable.avatar_sarah,
                restoName = "Bakso Solo Samrat"
            ),
            ExploreReview(
                name = "Dinda P.",
                badge = "Coffee Hunter",
                level = "Level 4 Explorer",
                place = "Kopi Kenangan, Jakarta Selatan",
                taste = "9.2/10",
                atmosphere = "8.7/10",
                price = "Worth It",
                description = "Kopi susunya creamy dengan rasa gula aren yang pas. Roti cokelatnya cocok banget buat teman nugas atau nongkrong sore!",
                image = R.drawable.kopi_kenangan_food,
                avatar = R.drawable.avatar_sarah,
                restoName = "Kopi Kenangan"
            ),
            ExploreReview(
                name = "Raka M.",
                badge = "Cafe Explorer",
                level = "Level 5 Explorer",
                place = "Kopi Nako, Jakarta Selatan",
                taste = "9.0/10",
                atmosphere = "9.1/10",
                price = "Worth It",
                description = "Kopi dinginnya menyegarkan dan roti panggangnya cocok buat camilan. Tempatnya nyaman buat ngobrol santai bareng teman!",
                image = R.drawable.kopi_nako_food,
                avatar = R.drawable.avatar_sarah,
                restoName = "Kopi Nako"
            )
        )
    }

    val visibleReviews = reviews.filter {
        query.isBlank() ||
                it.name.contains(query, ignoreCase = true) ||
                it.place.contains(query, ignoreCase = true) ||
                it.description.contains(query, ignoreCase = true)
    }

    Scaffold(
        containerColor = ExploreBackground,
        bottomBar = {
            ExploreNavigation(onNavigate)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 18.dp)
        ) {
            item {
                ExploreTopBar(onNavigate)
            }

            item {
                Column(
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 3.dp,
                        bottom = 12.dp
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "⚙",
                            color = ExploreRed,
                            fontSize = 16.sp
                        )

                        Spacer(Modifier.width(6.dp))

                        Text(
                            text = "SUARA KOMUNITAS",
                            color = ExploreRed,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(Modifier.weight(1f))

                        Surface(
                            color = Color(0xFFF0EBF4),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(
                                    horizontal = 7.dp,
                                    vertical = 4.dp
                                ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    Modifier
                                        .size(6.dp)
                                        .background(
                                            Color(0xFF83BFA0),
                                            CircleShape
                                        )
                                )

                                Spacer(Modifier.width(4.dp))

                                Text(
                                    text = "2.4K Foto Hari Ini",
                                    color = ExploreMuted,
                                    fontSize = 9.sp
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Review Jujur Foodies 📸",
                        color = ExploreText,
                        fontSize = 25.sp,
                        lineHeight = 30.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = "Eksplorasi kuliner nyata dari food hunters terpercaya di sekitarmu.",
                        color = ExploreMuted,
                        fontSize = 13.sp,
                        lineHeight = 19.sp
                    )

                    Spacer(Modifier.height(12.dp))

                    ExploreSearch(
                        query = query,
                        onQueryChange = { query = it }
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(bottom = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Spacer(Modifier.width(8.dp))

                    listOf(
                        "Terbaru",
                        "Foto Terbaik",
                        "Paling Viral",
                        "Rekomendasi"
                    ).forEach { filter ->
                        ExploreFilterChip(
                            label = filter,
                            selected = selectedFilter == filter,
                            onClick = { selectedFilter = filter }
                        )
                    }

                    Spacer(Modifier.width(8.dp))
                }
            }

            if (visibleReviews.isEmpty()) {
                item {
                    Text(
                        text = "Belum ada review yang cocok.",
                        color = ExploreMuted,
                        modifier = Modifier.padding(24.dp),
                        fontSize = 13.sp
                    )
                }
            } else {
                items(visibleReviews) { review ->
                    ExploreReviewCard(
                        review = review,
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 5.dp
                        ),
                        onClick = {
                            // Kirim nama restoran yang dipilih ke MainActivity.
                            onNavigate(review.restoName)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ExploreTopBar(
    onNavigate: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ExploreBackground)
            .padding(horizontal = 16.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            color = ExploreRed,
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(
                text = "Y",
                color = Color.White,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    horizontal = 7.dp,
                    vertical = 5.dp
                )
            )
        }

        Spacer(Modifier.width(8.dp))

        Surface(
            color = ExploreLavender,
            shape = RoundedCornerShape(22.dp),
            modifier = Modifier.clickable {
                onNavigate("location")
            }
        ) {
            Text(
                text = "📍 Jakarta Selatan  ⌄",
                color = ExploreText,
                fontSize = 11.sp,
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                    vertical = 9.dp
                )
            )
        }

        Spacer(Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    onNavigate("notifications")
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "♧",
                color = ExploreText,
                fontSize = 23.sp
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 3.dp, end = 3.dp)
                    .size(7.dp)
                    .background(ExploreRed, CircleShape)
            )
        }

        Spacer(Modifier.width(8.dp))

        Image(
            painter = painterResource(R.drawable.avatar_sarah),
            contentDescription = "Profil pengguna",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(31.dp)
                .clip(CircleShape)
                .clickable {
                    onNavigate("profile")
                }
        )
    }
}

@Composable
private fun ExploreSearch(
    query: String,
    onQueryChange: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(43.dp),
        color = ExploreLavender,
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "⌕",
                color = ExploreText,
                fontSize = 24.sp
            )

            Spacer(Modifier.width(8.dp))

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                if (query.isEmpty()) {
                    Text(
                        text = "Cari bakso, matcha latte, atau resto favorit",
                        fontSize = 12.sp,
                        color = ExploreMuted,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        color = ExploreText
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(Modifier.width(8.dp))

            Text(
                text = "♩",
                color = ExploreRed,
                fontSize = 19.sp
            )
        }
    }
}

@Composable
private fun ExploreFilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val icon = when (label) {
        "Terbaru" -> "◷"
        "Foto Terbaik" -> "▧"
        "Paling Viral" -> "♨"
        else -> "☆"
    }

    Surface(
        modifier = Modifier.clickable(onClick = onClick),
        color = if (selected) ExploreRed else ExploreLavender,
        shape = RoundedCornerShape(22.dp)
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 9.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = icon,
                color = if (selected) Color.White else ExploreRed,
                fontSize = 12.sp
            )

            Spacer(Modifier.width(5.dp))

            Text(
                text = label,
                color = if (selected) Color.White else ExploreText,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun ExploreReviewCard(
    review: ExploreReview,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(17.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        top = 12.dp,
                        bottom = 8.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(review.avatar),
                    contentDescription = review.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                )

                Spacer(Modifier.width(9.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = review.name,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ExploreText
                        )

                        Spacer(Modifier.width(6.dp))

                        Surface(
                            color = Color(0xFFFFE4D8),
                            shape = RoundedCornerShape(7.dp)
                        ) {
                            Text(
                                text = review.badge,
                                color = ExploreRed,
                                fontSize = 8.sp,
                                modifier = Modifier.padding(
                                    horizontal = 5.dp,
                                    vertical = 3.dp
                                ),
                                maxLines = 1
                            )
                        }
                    }

                    Spacer(Modifier.height(3.dp))

                    Text(
                        text = "♧ 2 jam lalu • ${review.level}",
                        fontSize = 9.sp,
                        color = ExploreMuted,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Text(
                    text = "•••",
                    color = ExploreMuted,
                    fontSize = 15.sp
                )
            }

            Surface(
                color = Color.White,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 8.dp
                    )
                    .border(
                        width = 1.dp,
                        color = ExploreBorder,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = 9.dp,
                        vertical = 7.dp
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⌖",
                        color = ExploreRed,
                        fontSize = 15.sp
                    )

                    Spacer(Modifier.width(5.dp))

                    Text(
                        text = review.place,
                        color = ExploreText,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(Modifier.width(5.dp))

                    Text(
                        text = "›",
                        color = ExploreMuted,
                        fontSize = 19.sp
                    )
                }
            }

            Box {
                Image(
                    painter = painterResource(review.image),
                    contentDescription = "Foto makanan",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(244.dp)
                )

                Surface(
                    color = ExploreGreen,
                    shape = RoundedCornerShape(
                        topStart = 10.dp,
                        bottomStart = 10.dp
                    ),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = "✓ Dine-in Terverifikasi",
                        color = Color(0xFF39845D),
                        fontSize = 9.sp,
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 5.dp
                        )
                    )
                }

                Surface(
                    color = Color(0xCC29252A),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(9.dp)
                ) {
                    Text(
                        text = "▣ 1/4",
                        color = Color.White,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(
                            horizontal = 7.dp,
                            vertical = 4.dp
                        )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(
                        start = 12.dp,
                        top = 9.dp,
                        bottom = 8.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                ExploreRating(
                    label = "♨ Rasa: ${review.taste}",
                    background = Color(0xFFFFE9E2),
                    foreground = ExploreRed
                )

                ExploreRating(
                    label = "◉ Suasana: ${review.atmosphere}",
                    background = Color(0xFFE5F4F1),
                    foreground = Color(0xFF287F75)
                )

                ExploreRating(
                    label = "♧ ${review.price}",
                    background = Color(0xFFFFEBCB),
                    foreground = Color(0xFF8B5B17)
                )
            }

            Text(
                text = "“${review.description}”",
                color = ExploreText,
                fontSize = 13.sp,
                lineHeight = 22.sp,
                modifier = Modifier.padding(
                    start = 13.dp,
                    end = 13.dp,
                    bottom = 15.dp
                )
            )
        }
    }
}

@Composable
private fun ExploreRating(
    label: String,
    background: Color,
    foreground: Color
) {
    Surface(
        color = background,
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            text = label,
            color = foreground,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 5.dp
            )
        )
    }
}

@Composable
private fun ExploreNavigation(
    onNavigate: (String) -> Unit
) {
    Surface(
        color = Color.White,
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(66.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ExploreNavItem(
                modifier = Modifier.weight(1f),
                icon = "♜",
                label = "Beranda",
                selected = false,
                onClick = { onNavigate("home") }
            )

            ExploreNavItem(
                modifier = Modifier.weight(1f),
                icon = "◎",
                label = "Jelajah",
                selected = true,
                onClick = { onNavigate("explore") }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(49.dp)
                        .offset(y = (-6).dp)
                        .clip(CircleShape)
                        .background(ExploreRed)
                        .border(
                            width = 3.dp,
                            color = Color(0xFFFFE8DF),
                            shape = CircleShape
                        )
                        .clickable {
                            onNavigate("voice")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "♩",
                        color = Color.White,
                        fontSize = 25.sp
                    )
                }

                Text(
                    text = "Voice AI",
                    color = ExploreMuted,
                    fontSize = 9.sp,
                    modifier = Modifier.offset(y = (-5).dp)
                )
            }

            ExploreNavItem(
                modifier = Modifier.weight(1f),
                icon = "▤",
                label = "Peta",
                selected = false,
                onClick = { onNavigate("map") }
            )

            ExploreNavItem(
                modifier = Modifier.weight(1f),
                icon = "♙",
                label = "Profil",
                selected = false,
                onClick = { onNavigate("profile") }
            )
        }
    }
}

@Composable
private fun ExploreNavItem(
    modifier: Modifier = Modifier,
    icon: String,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = icon,
            color = if (selected) ExploreRed else ExploreMuted,
            fontSize = 22.sp
        )

        Spacer(Modifier.height(3.dp))

        Text(
            text = label,
            color = if (selected) ExploreRed else ExploreMuted,
            fontSize = 10.sp,
            fontWeight = if (selected) {
                FontWeight.SemiBold
            } else {
                FontWeight.Normal
            }
        )
    }
}
