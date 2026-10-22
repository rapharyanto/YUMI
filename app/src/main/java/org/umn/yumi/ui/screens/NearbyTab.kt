package org.umn.yumi.ui.screens.homepage

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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.yumi.R
import org.umn.yumi.ui.theme.*

@Composable
fun HomeNearby(
    selectedTab: String = "Nearby",
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
                            .background(Color.Transparent)
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
                                colorFilter = ColorFilter.tint(YumiRed),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "For You",
                                color = YumiRed,
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
                            .background(
                                brush = Brush.linearGradient(
                                    0f to YumiRed,
                                    1f to YumiAccentOrange
                                )
                            )
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
                                colorFilter = ColorFilter.tint(YumiCream),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Nearby",
                                color = YumiCream,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // box lokasi titik kamu saat ini
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .border(BorderStroke(1.dp, Color(0xffede6d5)), RoundedCornerShape(14.dp))
                    .background(YumiCardCream)
                    .shadow(elevation = 1.dp, shape = RoundedCornerShape(14.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xffdbf4dc)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_titik_sekarang),
                                contentDescription = "Location Pin",
                                colorFilter = ColorFilter.tint(Color(0xff075b28)),
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            Text(
                                text = "Titik Kamu Saat Ini",
                                color = Color(0xff59413c),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Universitas Multimedia Nusantara",
                                color = Color(0xff59413c),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Surface(
                        shape = RoundedCornerShape(30.dp),
                        color = Color(0xfffaebdb),
                        shadowElevation = 1.dp,
                        modifier = Modifier.clickable { /* action ubah lokasi */ }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "Ubah",
                                color = Color(0xffae3115),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_ubah_lokasi),
                                contentDescription = "Edit",
                                colorFilter = ColorFilter.tint(Color(0xffae3115)),
                                modifier = Modifier.size(11.dp)
                            )
                        }
                    }
                }
            }

            // filter chips horizontal
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val filters = listOf("Buka Sekarang", "Rating 4.5+", "Halal", "Harga")
                items(filters) { filter ->
                    Surface(
                        shape = RoundedCornerShape(30.dp),
                        color = YumiCardCream,
                        shadowElevation = 1.dp,
                        border = BorderStroke(1.dp, Color(0xffede6d5)),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            val iconRes = when (filter) {
                                "Buka Sekarang" -> R.drawable.ic_jam_buka
                                "Rating 4.5+" -> R.drawable.ic_bintang_rating
                                "Halal" -> R.drawable.ic_halal
                                else -> R.drawable.ic_harga
                            }
                            Image(
                                painter = painterResource(id = iconRes),
                                contentDescription = filter,
                                colorFilter = ColorFilter.tint(Color(0xffae3115)),
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = filter,
                                color = Color(0xffae3115),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_urut_resto_terdekat),
                                contentDescription = "Near Icon",
                                colorFilter = ColorFilter.tint(YumiBlackSubTitle),
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "Terdekat dari Kamu",
                                color = YumiBlackSubTitle,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Text(
                            text = "Jarak dihitung akurat berdasarkan lokasi GPS saat ini",
                            color = YumiTextDark,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(30.dp),
                        color = Color(0xfffaebdb),
                        shadowElevation = 1.dp,
                        modifier = Modifier.clickable { /* action buka peta */ }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_navbar_peta),
                                contentDescription = "Map",
                                colorFilter = ColorFilter.tint(Color(0xffae3115)),
                                modifier = Modifier.size(12.dp)
                            )
                            Text(
                                text = "Buka Peta",
                                color = Color(0xffae3115),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1
                            )
                        }
                    }
                }
            }

            val nearbyList = listOf(
                Triple(R.drawable.kopi_kenangan_food, "Kopi Kenangan", "Kedai Kopi, Roti & Minuman"),
                Triple(R.drawable.kwetiau_aho, "Kwetiau 28 AHo", "Restoran China"),
                Triple(R.drawable.mekdi, "McDonald's", "Restoran Cepat Saji"),
                Triple(R.drawable.podjok_nusantara, "Podjok Selera Nusantara", "Restoran Indonesia"),
                Triple(R.drawable.warton, "Warton Gading Serpong", "Warung Tegal"),
                Triple(R.drawable.five_monkeys, "Five Monkeys Burger", "Restoran Hamburger"),
                Triple(R.drawable.bu_yudhi, "Kedai Sambal Cobek Bu Yudhi", "Ayam & Bebek, Aneka Sambal")
            )

            val nearbyDetailsList = listOf(
                NearbyDetails(price = "Rp15.000 - Rp45.000 per orang", hours = "Buka 08.00 - 22.00", location = "Ruko Bolsena, Kabupaten Tangerang", distance = "±2.2 km", rating = "4.8", viewers = "15.4K", tags = listOf("Halal", "Kopi", "Roti", "Enak", "Nongkrong")),
                NearbyDetails(price = "Rp50.000 - Rp75.000 per orang", hours = "Buka 09.00 - 23.00", location = "Summarecon Digital Center, Kabupaten Tangerang", distance = "±300 m", rating = "4.5", viewers = "1.2K", tags = listOf("Halal", "Kwetiau Sapi", "Nasi Goreng", "Enak", "Garansi")),
                NearbyDetails(price = "Rp30.000 - Rp75.000 per orang", hours = "Buka 24 Jam", location = "Summarecon Digital Center, Kabupaten Tangerang", distance = "±300 m", rating = "4.7", viewers = "17.0K", tags = listOf("Halal", "Ayam Spicy", "Es Krim", "Burger", "Wi-Fi")),
                NearbyDetails(price = "Rp25.000 - Rp50.000 per orang", hours = "Buka 10.00 - 22.00", location = "8N & 8M, Jl. Quantum Raya, Kabupaten Tangerang", distance = "±390 m", rating = "3.3", viewers = "1.3K", tags = listOf("Halal", "Rumahan", "Dekat Kampus", "Murah Meriah")),
                NearbyDetails(price = "Rp25.000 - Rp50.000 per orang", hours = "Buka 09.00 - 17.00", location = "Ruko Newton Barat, Kabupaten Tangerang", distance = "±450 m", rating = "3.5", viewers = "2.4K", tags = listOf("Halal", "Prasmanan", "Porsi Mantap", "Murah Meriah")),
                NearbyDetails(price = "Rp75.000 - Rp100.000 per orang", hours = "Buka 10.00 - 21.00", location = "Bez Walk South Wing, Kabupaten Tangerang", distance = "±742 km", rating = "4.2", viewers = "5.6K", tags = listOf("Halal", "Burger", "Daging Juicy", "Aesthetic")),
                NearbyDetails(price = "Rp20.000 - Rp50.000 per orang", hours = "Buka 10.00 - 19.30", location = "Medang, Pagedangan, Kabupaten Tangerang", distance = "±1.1 km", rating = "4.0", viewers = "10.2K", tags = listOf("Halal", "Pedas", "Ayam Penyet", "Murah", "Ramai"))
            )

            nearbyList.forEachIndexed { index, item ->
                val details = nearbyDetailsList[index]
                item {
                    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                        NearbyRestaurantCardItem(
                            imageRes = item.first,
                            title = item.second,
                            category = item.third,
                            price = details.price,
                            hours = details.hours,
                            location = details.location,
                            distance = details.distance,
                            rating = details.rating,
                            viewers = details.viewers,
                            tags = details.tags
                        )
                    }
                }
            }
        }
    }
}

data class NearbyDetails(
    val price: String,
    val hours: String,
    val location: String,
    val distance: String,
    val rating: String,
    val viewers: String,
    val tags: List<String>
)

@Composable
fun NearbyRestaurantCardItem(
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
                        color = YumiTextDark,
                        textAlign = TextAlign.End,
                        lineHeight = 14.sp
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
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(tags) { tag ->
                        val (bgColor, textColor) = when (tag) {
                            "Halal" -> Pair(YumiGreenBG, YumiGreen)
                            "Aesthetic", "Wi-Fi" -> Pair(YumiBlueBG, YumiBlue)
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
private fun HomeNearbyPreview() {
    YumiTheme {
        var currentTab by remember { mutableStateOf("Nearby") }
        if (currentTab == "Nearby") {
            HomeNearby(
                selectedTab = currentTab,
                onTabSelected = { newTab -> currentTab = newTab }
            )
        } else {
            HomeForYou(
                selectedTab = currentTab,
                onTabSelected = { newTab -> currentTab = newTab }
            )
        }
    }
}