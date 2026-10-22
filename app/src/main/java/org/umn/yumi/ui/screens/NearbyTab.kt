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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.yumi.R
import org.umn.yumi.ui.theme.*

@Composable
fun HomeNearby(
    selectedTab: String = "Nearby",
    onTabSelected: (String) -> Unit = {},
    onRestoClick: (String) -> Unit = {},
    onMapClick: () -> Unit = {},
    onShareClick: (String) -> Unit = {},
    onBookmarkClick: (String) -> Unit = {},
    bookmarkedItems: Set<String> = emptySet()
) {
    var filterOpenNow by remember { mutableStateOf(false) }
    var filterRatingHigh by remember { mutableStateOf(false) }
    var filterHalalOnly by remember { mutableStateOf(false) }

    var selectedPriceRange by remember { mutableStateOf<String?>(null) }
    var tempSelectedPriceRange by remember { mutableStateOf<String?>(null) }
    var showPriceDialog by remember { mutableStateOf(false) }

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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(BorderStroke(1.dp, Color(0xffede6d5)), RoundedCornerShape(14.dp))
                    .background(YumiCardCream)
                    .padding(horizontal = 12.dp, vertical = 12.dp),
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
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Titik Kamu Saat Ini",
                                color = Color(0xff59413c),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Senopati",
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
                        modifier = Modifier.clickable { }
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

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val filters = listOf("Buka Sekarang", "Rating 4.5+", "Halal", "Harga")
                items(filters) { filter ->
                    val isSelected = when (filter) {
                        "Buka Sekarang" -> filterOpenNow
                        "Rating 4.5+" -> filterRatingHigh
                        "Halal" -> filterHalalOnly
                        "Harga" -> selectedPriceRange != null
                        else -> false
                    }

                    val chipBg = if (isSelected) YumiRed else YumiCardCream
                    val chipText = if (isSelected) YumiCream else Color(0xffae3115)

                    Surface(
                        shape = RoundedCornerShape(30.dp),
                        color = chipBg,
                        shadowElevation = 1.dp,
                        border = BorderStroke(1.dp, if (isSelected) YumiRed else Color(0xffede6d5)),
                        modifier = Modifier
                            .height(32.dp)
                            .clickable {
                                when (filter) {
                                    "Buka Sekarang" -> filterOpenNow = !filterOpenNow
                                    "Rating 4.5+" -> filterRatingHigh = !filterRatingHigh
                                    "Halal" -> filterHalalOnly = !filterHalalOnly
                                    "Harga" -> {
                                        tempSelectedPriceRange = selectedPriceRange
                                        showPriceDialog = true
                                    }
                                }
                            }
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
                                colorFilter = ColorFilter.tint(chipText),
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = if (filter == "Harga" && selectedPriceRange != null) selectedPriceRange!! else filter,
                                color = chipText,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            val rawNearbyList = listOf(
                Triple(R.drawable.kopi_kenangan_food, "Kopi Kenangan", "Kedai Kopi, Roti & Minuman"),
                Triple(R.drawable.kwetiau_aho, "Kwetiau 28 AHo", "Restoran China"),
                Triple(R.drawable.mekdi, "McDonald's", "Restoran Cepat Saji"),
                Triple(R.drawable.podjok_nusantara, "Podjok Selera Nusantara", "Restoran Indonesia"),
                Triple(R.drawable.warton, "Warteg Kharisma Bahari", "Warung Tegal"),
                Triple(R.drawable.five_monkeys, "Five Monkeys Burger", "Restoran Hamburger"),
            )

            val rawNearbyDetailsList = listOf(
                NearbyDetails(price = "Rp15.000 - Rp45.000 per orang", minPrice = 15000, maxPrice = 45000, hours = "Buka 08.00 - 22.00", location = "Ruko Jalan Jeruk Jagakarsa, Jakarta Selatan", distance = "±2.2 km", rating = 4.8f, viewers = "15.4K", tags = listOf("Halal", "Kopi", "Roti", "Enak", "Nongkrong")),
                NearbyDetails(price = "Rp50.000 - Rp75.000 per orang", minPrice = 50000, maxPrice = 75000, hours = "Buka 09.00 - 23.00", location = "Pondok Indah Mall 2, Jakarta Selatan", distance = "±300 m", rating = 4.5f, viewers = "1.2K", tags = listOf("Halal", "Kwetiau Sapi", "Nasi Goreng", "Enak", "Garansi")),
                NearbyDetails(price = "Rp30.000 - Rp75.000 per orang", minPrice = 30000, maxPrice = 75000, hours = "Buka 24 Jam", location = "Tebet, Jakarta Selatan", distance = "±300 m", rating = 4.7f, viewers = "17.0K", tags = listOf("Halal", "Ayam Spicy", "Es Krim", "Burger", "Wi-Fi")),
                NearbyDetails(price = "Rp25.000 - Rp50.000 per orang", minPrice = 25000, maxPrice = 50000, hours = "Buka 10.00 - 22.00", location = "Blok M, Jakarta Selatan", distance = "±390 m", rating = 3.3f, viewers = "1.3K", tags = listOf("Halal", "Rumahan", "Dekat Kampus", "Murah Meriah")),
                NearbyDetails(price = "Rp25.000 - Rp50.000 per orang", minPrice = 25000, maxPrice = 50000, hours = "Buka 09.00 - 17.00", location = "Senayan, Jakarta Selatan", distance = "±450 m", rating = 3.5f, viewers = "2.4K", tags = listOf("Halal", "Prasmanan", "Porsi Mantap", "Murah Meriah")),
                NearbyDetails(price = "Rp75.000 - Rp100.000 per orang", minPrice = 75000, maxPrice = 100000, hours = "Buka 10.00 - 21.00", location = "Plaza Senayan, Jakarta Selatan", distance = "±742 km", rating = 4.2f, viewers = "5.6K", tags = listOf("Halal", "Burger", "Daging Juicy", "Aesthetic"))
            )

            val filteredIndices = rawNearbyList.indices.filter { idx ->
                val details = rawNearbyDetailsList[idx]
                val matchOpen = !filterOpenNow || details.hours.contains("Buka", ignoreCase = true)
                val matchRating = !filterRatingHigh || details.rating >= 4.5f
                val matchHalal = !filterHalalOnly || details.tags.contains("Halal")

                val matchPrice = when (selectedPriceRange) {
                    "<50rb" -> details.minPrice < 50000
                    "50rb-100rb" -> details.minPrice in 50000..100000 || details.maxPrice in 50000..100000
                    "100rb-250rb" -> details.minPrice in 100000..250000 || details.maxPrice in 100000..250000
                    ">250rb" -> details.maxPrice > 250000
                    else -> true
                }

                matchOpen && matchRating && matchHalal && matchPrice
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
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
                        text = "Menampilkan ${filteredIndices.size} tempat makan sesuai filter",
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
                    modifier = Modifier.clickable { onMapClick() }
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

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(top = 4.dp, bottom = 100.dp)
            ) {
                if (filteredIndices.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Tidak ada restoran yang cocok dengan filtermu 🥺",
                                color = YumiTextDark,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                } else {
                    filteredIndices.forEach { index ->
                        val item = rawNearbyList[index]
                        val details = rawNearbyDetailsList[index]
                        item {
                            NearbyRestaurantCardItem(
                                imageRes = item.first,
                                title = item.second,
                                category = item.third,
                                price = details.price,
                                hours = details.hours,
                                location = details.location,
                                distance = details.distance,
                                rating = details.rating.toString(),
                                viewers = details.viewers,
                                tags = details.tags,
                                isBookmarked = bookmarkedItems.contains(item.second),
                                onCardClick = { onRestoClick(item.second) },
                                onShareClick = { onShareClick(item.second) },
                                onBookmarkClick = { onBookmarkClick(item.second) }
                            )
                        }
                    }
                }
            }
        }
    }

    if (showPriceDialog) {
        val priceOptions = listOf("<50rb", "50rb-100rb", "100rb-250rb", ">250rb")

        AlertDialog(
            onDismissRequest = { showPriceDialog = false },
            title = {
                Text(
                    text = "Pilih Filter Harga",
                    fontWeight = FontWeight.Bold,
                    color = YumiBlackSubTitle,
                    fontSize = 18.sp
                )
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Pilih estimasi budget makan per orang:",
                        fontSize = 12.sp,
                        color = YumiTextDark
                    )

                    priceOptions.forEach { option ->
                        val isSelectedOption = (tempSelectedPriceRange == option)
                        val chipBg = if (isSelectedOption) YumiRed else YumiCardCream
                        val textColor = if (isSelectedOption) YumiCream else YumiTextDark

                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = chipBg,
                            border = BorderStroke(1.dp, if (isSelectedOption) YumiRed else Color(0xffede6d5)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    tempSelectedPriceRange = if (isSelectedOption) null else option
                                }
                        ) {
                            Box(
                                modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = option,
                                    fontSize = 14.sp,
                                    fontWeight = if (isSelectedOption) FontWeight.Bold else FontWeight.Medium,
                                    color = textColor
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = YumiRed),
                    onClick = {
                        selectedPriceRange = tempSelectedPriceRange
                        showPriceDialog = false
                    }
                ) {
                    Text("Terapkan", color = YumiCream, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        tempSelectedPriceRange = null
                        selectedPriceRange = null
                        showPriceDialog = false
                    }
                ) {
                    Text("Reset", color = YumiRed)
                }
            },
            containerColor = YumiCream,
            shape = RoundedCornerShape(20.dp)
        )
    }
}

data class NearbyDetails(
    val price: String,
    val minPrice: Int,
    val maxPrice: Int,
    val hours: String,
    val location: String,
    val distance: String,
    val rating: Float,
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
    tags: List<String>,
    isBookmarked: Boolean,
    onCardClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookmarkClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(containerColor = YumiCardCream),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick() }
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
                    .clickable { onBookmarkClick() }
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_bookmark),
                        contentDescription = "Bookmark",
                        colorFilter = ColorFilter.tint(if (isBookmarked) YumiRed else YumiTextDark),
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
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onShareClick() }
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