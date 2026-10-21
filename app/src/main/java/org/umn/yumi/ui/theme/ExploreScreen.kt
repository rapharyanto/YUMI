
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
import org.umn.yumi.ui.components.BottomNavigationBar

private val ExploreRed = YumiRed
private val ExploreDarkRed = YumiDarkRed
private val ExploreBackground = YumiCream
private val ExploreSurface = YumiWhite
private val ExploreText = YumiText
private val ExploreMuted = YumiMutedText
private val ExploreBorder = YumiBorder

private val ExploreSoftRed = Color(0xFFF7E5DF)
private val ExploreSoftGreen = Color(0xFFE4F0E4)
private val ExploreGreenText = Color(0xFF47734D)
private val ExploreSoftGold = Color(0xFFFFEBCB)
private val ExploreGoldText = Color(0xFF8B5B17)

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
                it.description.contains(query, ignoreCase = true) ||
                it.restoName.contains(query, ignoreCase = true)
    }

    Scaffold(
        containerColor = ExploreBackground,
        bottomBar = {
            BottomNavigationBar(
                selectedMenu = "jelajah",
                onMenuSelected = { menu ->
                    when (menu) {
                        "beranda" -> onNavigate("home")
                        "jelajah" -> onNavigate("explore")
                        "peta" -> onNavigate("map")
                        "profil" -> onNavigate("profile")
                        "voice_ai" -> onNavigate("voice")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(ExploreBackground)
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            item {
                ExploreTopBar(onNavigate)
            }

            item {
                Column(
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 15.dp,
                        bottom = 14.dp
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(25.dp)
                                .background(
                                    ExploreSoftRed,
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "✦",
                                color = ExploreRed,
                                fontSize = 14.sp
                            )
                        }

                        Spacer(Modifier.width(8.dp))

                        Text(
                            text = "SUARA KOMUNITAS",
                            color = ExploreRed,
                            fontSize = 10.sp,
                            letterSpacing = 1.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.weight(1f))

                        Surface(
                            color = ExploreSurface,
                            shape = RoundedCornerShape(20.dp),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                ExploreBorder
                            )
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
                                        .size(6.dp)
                                        .background(
                                            ExploreGreenText,
                                            CircleShape
                                        )
                                )

                                Spacer(Modifier.width(5.dp))

                                Text(
                                    text = "2.4K Foto Hari Ini",
                                    color = ExploreMuted,
                                    fontSize = 9.sp
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = "Review Jujur Foodies",
                        color = ExploreText,
                        fontSize = 25.sp,
                        lineHeight = 31.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(5.dp))

                    Text(
                        text = "Temukan cerita dan rekomendasi kuliner dari food hunters.",
                        color = ExploreMuted,
                        fontSize = 12.sp,
                        lineHeight = 18.sp
                    )

                    Spacer(Modifier.height(16.dp))

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
                        .padding(bottom = 14.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Spacer(Modifier.width(12.dp))

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

                    Spacer(Modifier.width(12.dp))
                }
            }

            if (visibleReviews.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "⌕",
                            color = ExploreRed,
                            fontSize = 34.sp
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = "Belum ada review yang cocok",
                            color = ExploreText,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "Coba kata kunci lainnya, bro.",
                            color = ExploreMuted,
                            fontSize = 12.sp
                        )
                    }
                }
            } else {
                items(visibleReviews) { review ->
                    ExploreReviewCard(
                        review = review,
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 7.dp
                        ),
                        onClick = {
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
            .background(ExploreRed)
            .padding(
                horizontal = 18.dp,
                vertical = 13.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            color = ExploreCreamLogo,
            shape = RoundedCornerShape(9.dp)
        ) {
            Text(
                text = "Y",
                color = ExploreRed,
                fontSize = 17.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(
                    horizontal = 11.dp,
                    vertical = 7.dp
                )
            )
        }

        Spacer(Modifier.width(10.dp))

        Surface(
            color = ExploreCreamLogo,
            shape = RoundedCornerShape(22.dp),
            modifier = Modifier.clickable {
                onNavigate("location")
            }
        ) {
            Row(
                modifier = Modifier.padding(
                    horizontal = 11.dp,
                    vertical = 9.dp
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
                    text = "Jakarta Selatan",
                    color = ExploreDarkRed,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.width(5.dp))

                Text(
                    text = "⌄",
                    color = ExploreDarkRed,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .clickable {
                    onNavigate("notifications")
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "♧",
                color = ExploreCreamLogo,
                fontSize = 23.sp
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 4.dp, end = 4.dp)
                    .size(7.dp)
                    .background(
                        Color(0xFFFFD56A),
                        CircleShape
                    )
            )
        }

        Spacer(Modifier.width(8.dp))

        Image(
            painter = painterResource(R.drawable.avatar_sarah),
            contentDescription = "Profil pengguna",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(34.dp)
                .border(
                    2.dp,
                    ExploreCreamLogo,
                    CircleShape
                )
                .clip(CircleShape)
                .clickable {
                    onNavigate("profile")
                }
        )
    }
}

private val ExploreCreamLogo = Color(0xFFFFFBEB)

@Composable
private fun ExploreSearch(
    query: String,
    onQueryChange: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(
                1.dp,
                ExploreBorder,
                RoundedCornerShape(24.dp)
            ),
        color = ExploreSurface,
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "⌕",
                color = ExploreText,
                fontSize = 25.sp
            )

            Spacer(Modifier.width(9.dp))

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                if (query.isEmpty()) {
                    Text(
                        text = "Cari makanan, kopi susu, ramen...",
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

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(
                        ExploreSoftRed,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "≡",
                    color = ExploreRed,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
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
        modifier = Modifier
            .clickable(onClick = onClick)
            .then(
                if (selected) Modifier
                else Modifier.border(
                    1.dp,
                    ExploreBorder,
                    RoundedCornerShape(22.dp)
                )
            ),
        color = if (selected) ExploreRed else ExploreSurface,
        shape = RoundedCornerShape(22.dp)
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 13.dp,
                vertical = 10.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = icon,
                color = if (selected) ExploreSurface else ExploreRed,
                fontSize = 12.sp
            )

            Spacer(Modifier.width(6.dp))

            Text(
                text = label,
                color = if (selected) ExploreSurface else ExploreText,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
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
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = ExploreSurface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 13.dp,
                        end = 13.dp,
                        top = 13.dp,
                        bottom = 10.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(review.avatar),
                    contentDescription = review.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                )

                Spacer(Modifier.width(10.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = review.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = ExploreText,
                        maxLines = 1
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = "${review.level} · 2 jam lalu",
                        fontSize = 10.sp,
                        color = ExploreMuted,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(Modifier.height(5.dp))

                    Surface(
                        color = ExploreSoftRed,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = review.badge,
                            color = ExploreDarkRed,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(
                                horizontal = 7.dp,
                                vertical = 4.dp
                            ),
                            maxLines = 1
                        )
                    }
                }

                Text(
                    text = "•••",
                    color = ExploreMuted,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 13.dp
                    )
                    .border(
                        1.dp,
                        ExploreBorder,
                        RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .background(ExploreSurface)
                    .clickable(onClick = onClick)
                    .padding(
                        horizontal = 10.dp,
                        vertical = 9.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "⌖",
                    color = ExploreRed,
                    fontSize = 16.sp
                )

                Spacer(Modifier.width(7.dp))

                Text(
                    text = review.place,
                    color = ExploreText,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "›",
                    color = ExploreMuted,
                    fontSize = 20.sp
                )
            }

            Spacer(Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .clip(RoundedCornerShape(15.dp))
            ) {
                Image(
                    painter = painterResource(review.image),
                    contentDescription = "Foto makanan di ${review.restoName}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .clickable(onClick = onClick)
                )

                Surface(
                    color = ExploreSoftGreen,
                    shape = RoundedCornerShape(
                        topEnd = 12.dp,
                        bottomStart = 12.dp
                    ),
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Text(
                        text = "✓ Terverifikasi",
                        color = ExploreGreenText,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(
                            horizontal = 9.dp,
                            vertical = 6.dp
                        )
                    )
                }

                Surface(
                    color = Color(0xCC3D2923),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(9.dp)
                ) {
                    Text(
                        text = "▧ 1/4",
                        color = ExploreSurface,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 5.dp
                        )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(
                        start = 13.dp,
                        end = 13.dp,
                        top = 11.dp,
                        bottom = 8.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                ExploreRating(
                    label = "✦ Rasa ${review.taste}",
                    background = ExploreSoftRed,
                    foreground = ExploreDarkRed
                )

                ExploreRating(
                    label = "◉ Suasana ${review.atmosphere}",
                    background = ExploreSoftGreen,
                    foreground = ExploreGreenText
                )

                ExploreRating(
                    label = "♧ ${review.price}",
                    background = ExploreSoftGold,
                    foreground = ExploreGoldText
                )
            }

            Text(
                text = review.restoName,
                color = ExploreText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    start = 14.dp,
                    end = 14.dp,
                    top = 2.dp
                )
            )

            Text(
                text = "“${review.description}”",
                color = ExploreMuted,
                fontSize = 12.sp,
                lineHeight = 19.sp,
                modifier = Modifier.padding(
                    start = 14.dp,
                    end = 14.dp,
                    top = 5.dp,
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
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = label,
            color = foreground,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(
                horizontal = 9.dp,
                vertical = 6.dp
            )
        )
    }
}
