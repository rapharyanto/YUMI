package org.umn.yumi.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.yumi.R
import org.umn.yumi.ui.theme.*

// Bentuk Wave Header
val HeaderWaveShape = GenericShape { size, _ ->
    moveTo(0f, 0f)
    lineTo(size.width, 0f)
    lineTo(size.width, size.height - 25f)
    cubicTo(
        size.width * 0.75f, size.height + 10f,
        size.width * 0.25f, size.height + 10f,
        0f, size.height - 25f
    )
    close()
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(95.dp)
            .clip(HeaderWaveShape)
            .background(color = YumiRed)
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_yumi_transparant),
                    contentDescription = "LOGO YUMI",
                    modifier = Modifier.size(70.dp)
                )

                Surface(
                    shape = RoundedCornerShape(100.dp),
                    color = YumiCream,
                    modifier = Modifier.height(38.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_location),
                            contentDescription = "Location Icon",
                            colorFilter = ColorFilter.tint(YumiRed),
                            modifier = Modifier.size(17.dp)
                        )
                        Text(
                            text = "Gading Serpong",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = YumiTextDark,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_panah_bawah),
                            contentDescription = "Dropdown Arrow",
                            modifier = Modifier.size(9.dp)
                        )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = "Notification",
                    modifier = Modifier.size(35.dp)
                )

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(color = YumiNeutralGrey)
                )
            }
        }
    }
}

@Composable
fun HomeForYou(
    selectedTab: String = "For You",
    onTabSelected: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(YumiCream)
    ) {
        Header()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(YumiCream)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // tab switcher
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(YumiRedCream)
                    .padding(4.dp)
            ) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(30.dp))
                            .background(
                                brush = Brush.linearGradient(
                                    0f to YumiRed,
                                    1f to YumiAccentOrange
                                )
                            )
                            .clickable { onTabSelected("For You") },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_for_you),
                                contentDescription = "For You Icon",
                                colorFilter = ColorFilter.tint(YumiCream),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "For You",
                                color = YumiCream,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(30.dp))
                            .background(Color.Transparent)
                            .clickable { onTabSelected("Nearby") },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_nearby),
                                contentDescription = "Nearby Icon",
                                colorFilter = ColorFilter.tint(YumiRed),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Nearby",
                                color = YumiRed,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // search bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .border(border = BorderStroke(1.dp, YumiShadowGrey), shape = RoundedCornerShape(100.dp))
                    .background(YumiCardCream)
                    .shadow(elevation = 1.dp, shape = RoundedCornerShape(100.dp))
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search Icon",
                        colorFilter = ColorFilter.tint(YumiTextDark),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Cari sate, kopi susu, ramen...",
                        color = YumiNeutralGrey,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 2.dp, bottom = 100.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Lagi BM Apa Nih?",
                        color = YumiBlackSubTitle,
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "Pilih mood kulinermu hari ini",
                        color = YumiTextDark,
                        style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val categories = listOf(
                            Pair("Kopi", R.drawable.kopi),
                            Pair("Pedas", R.drawable.pedas),
                            Pair("Mie", R.drawable.mie),
                            Pair("Cemilan", R.drawable.cemilan),
                            Pair("Dessert", R.drawable.desert)
                        )
                        categories.forEach { (name, imgRes) ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = imgRes),
                                    contentDescription = name,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(58.dp)
                                        .clip(CircleShape)
                                )
                                Text(
                                    text = name,
                                    color = YumiBlackSubTitle,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 2.dp)
                ) {
                    Card(
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.linearGradient(
                                        0f to YumiAccentOrange,
                                        1f to YumiRed
                                    )
                                )
                                .padding(14.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(6.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_voice_mode),
                                            contentDescription = "Voice Mode",
                                            colorFilter = ColorFilter.tint(YumiCream),
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Text(
                                            text = "VOICE AI SIAP BANTU",
                                            color = YumiNeutralGrey,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }

                                    Text(
                                        text = "“Cari makanan viral Blok M di bawah 50rb!”",
                                        color = YumiCream,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        lineHeight = 22.sp
                                    )

                                    Spacer(modifier = Modifier.height(2.dp))

                                    Surface(
                                        shape = RoundedCornerShape(100.dp),
                                        color = YumiCream,
                                        modifier = Modifier.height(34.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 12.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.ic_mic_ai),
                                                contentDescription = "Mic AI",
                                                colorFilter = ColorFilter.tint(YumiRed),
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Text(
                                                text = "Tanya Sekarang!",
                                                color = YumiRed,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }

                                Image(
                                    painter = painterResource(id = R.drawable.yumi_mikirmakanan),
                                    contentDescription = "Mascot AI",
                                    modifier = Modifier.size(140.dp)
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
                        .padding(start = 16.dp, end = 16.dp, top = 2.dp, bottom = 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_fire),
                                contentDescription = "Fire Icon",
                                colorFilter = ColorFilter.tint(YumiAccentOrange),
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "Eksplor Tanpa Batas",
                                color = YumiBlackSubTitle,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "Scroll untuk menemukan tempat makan hits!",
                            color = YumiTextDark,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Text(
                        text = "Lihat Semua",
                        color = YumiRed,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }

            val forYouList = listOf(
                Triple(R.drawable.sate_taichan_bang_yoyo, "Sate Taichan Bang Yoyo", "Sate & Grill, Street Food"),
                Triple(R.drawable.bu_yudhi, "Kedai Sambal Cobek Bu Yudhi", "Ayam & Bebek, Aneka Sambal"),
                Triple(R.drawable.secreet_sushi, "Secret Sushi by Okinawa Sushi", "Restoran Jepang, All You Can Eat"),
                Triple(R.drawable.obihiro_nikudon, "Obihiro Nikudon", "Restoran Jepang")
            )

            val forYouTagsList = listOf(
                listOf("Halal", "Pedas", "Sate Ayam", "Ramai", "Nongkrong"),
                listOf("Halal", "Pedas", "Ayam Penyet", "Murah", "Ramai"),
                listOf("Halal", "Jepang", "Sushi", "Sashimi", "Casual Dinner"),
                listOf("Halal", "Nikudon", "Enak", "Ramai", "Antrian Panjang")
            )

            forYouList.forEachIndexed { index, item ->
                item {
                    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                        RestaurantCardItem(
                            imageRes = item.first,
                            title = item.second,
                            category = item.third,
                            price = "Rp25.000 - Rp50.000 per orang",
                            hours = "Buka 11.00 - 23.00",
                            location = "Jakarta Selatan",
                            distance = "±35.1 km",
                            rating = "4.9",
                            viewers = "28.9K",
                            tags = forYouTagsList[index]
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RestaurantCardItem(
    imageRes: Int,
    title: String,
    category: String,
    price: String,
    hours: String,
    location: String,
    distance: String,
    rating: String,
    viewers: String,
    tags: List<String>
) {
    Card(
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(containerColor = YumiCardCream),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp)
                    .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                    .background(YumiCardCream)
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = YumiBlackSubTitle,
                        modifier = Modifier.weight(1f)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_bintang_rating),
                            contentDescription = "Rating",
                            colorFilter = ColorFilter.tint(YumiRatingYellow),
                            modifier = Modifier.size(15.dp)
                        )
                        Text(
                            text = rating,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = YumiRatingYellow
                        )
                    }
                }

                Text(
                    text = category,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic,
                    color = YumiTextDark
                )

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_harga),
                        contentDescription = "Price",
                        colorFilter = ColorFilter.tint(YumiGreen),
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = price,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = YumiGreen
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_jam_buka),
                            contentDescription = "Hours",
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = hours,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = YumiDarkYellow
                        )
                    }

                    Text(
                        text = distance,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = YumiTextDark
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "Location",
                        colorFilter = ColorFilter.tint(YumiRed),
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = location,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = YumiRed,
                        maxLines = 1
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(tags) { tag ->
                        // Dinamis menyesuaikan warna tag sesuai jenisnya
                        val (bgColor, textColor) = when (tag) {
                            "Halal" -> Pair(YumiGreenBG, YumiGreen)
                            "Nongkrong", "Casual Dinner", "Antrian Panjang" -> Pair(YumiBlueBG, YumiBlue)
                            else -> Pair(YumiOrangeBG, YumiOrangeSecondary)
                        }

                        Surface(
                            color = bgColor,
                            shape = RoundedCornerShape(100.dp)
                        ) {
                            Text(
                                text = tag,
                                fontSize = 12.sp,
                                color = textColor,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }

            Surface(
                color = YumiOrangeSecondary,
                shape = RoundedCornerShape(100.dp),
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.TopStart)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_viral),
                        contentDescription = "Viral",
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = "Viral",
                        color = YumiCream,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Surface(
                color = YumiCream.copy(alpha = 0.8f),
                shape = CircleShape,
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.TopEnd)
                    .size(32.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_bookmark),
                        contentDescription = "Bookmark",
                        colorFilter = ColorFilter.tint(YumiTextDark),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Surface(
                color = Color.Black.copy(alpha = 0.5f),
                shape = CircleShape,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 35.dp)
                    .size(50.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_play_video),
                        contentDescription = "Play Video",
                        colorFilter = ColorFilter.tint(YumiCream),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(y = 60.dp, x = (-10).dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Surface(
                    color = YumiCream.copy(alpha = 0.85f),
                    shape = RoundedCornerShape(100.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_viewers),
                            contentDescription = "Viewers",
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = viewers,
                            color = YumiTextDark,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Surface(
                    color = YumiCream.copy(alpha = 0.85f),
                    shape = CircleShape,
                    modifier = Modifier.size(28.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = "Share",
                            colorFilter = ColorFilter.tint(YumiTextDark),
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 412, heightDp = 800)
@Composable
private fun HomeForYouPreview() {
    YumiTheme {
        var currentTab by remember { mutableStateOf("For You") }
        if (currentTab == "For You") {
            HomeForYou(
                selectedTab = currentTab,
                onTabSelected = { newTab -> currentTab = newTab }
            )
        } else {
            HomeNearby(
                selectedTab = currentTab,
                onTabSelected = { newTab -> currentTab = newTab }
            )
        }
    }
}