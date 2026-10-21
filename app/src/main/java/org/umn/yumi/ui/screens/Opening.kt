package org.umn.yumi.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.umn.yumi.R
import org.umn.yumi.ui.theme.YumiTheme

// --- PALET WARNA KHUSUS OPENING ---
private val OpeningBg = Color(0xFFFDF8FF)
private val PrimaryOrange = Color(0xFFFF6B4A)
private val DarkRed = Color(0xFFAE3115)
private val TextDark = Color(0xFF1C1A28)
private val CardBg = Color(0xFFFFFFFF)
private val GreenAccent = Color(0xFF006C49)
private val YellowAccent = Color(0xFFD78900)
private val OpeningTextBrown = Color(0xFF59413C)

@Composable
fun OpeningScreen(
    onMulaiEksplorClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OpeningBg)
    ) {
        // --- AMBIENT GLOW BACKGROUND ---
        Box(
            modifier = Modifier
                .size(256.dp)
                .offset(x = 180.dp, y = 105.dp)
                .clip(CircleShape)
                .background(DarkRed.copy(alpha = 0.08f))
        )
        Box(
            modifier = Modifier
                .size(224.dp)
                .offset(x = (-64).dp, y = 450.dp)
                .clip(CircleShape)
                .background(YellowAccent.copy(alpha = 0.08f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(scrollState)
                .navigationBarsPadding()
                .padding(bottom = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // ================= 1. ANIMATED YUMI LOGO HEADER =================
            AnimatedYumiHeader()

            Spacer(modifier = Modifier.height(12.dp))

            // ================= 2. BENTO FOOD COLLAGE =================
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                BentoFoodCollage()

                // Floating Badge
                Surface(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 14.dp)
                        .clickable {
                            coroutineScope.launch {
                                scrollState.animateScrollTo(scrollState.maxValue)
                            }
                        },
                    shape = RoundedCornerShape(999.dp),
                    color = Color.White,
                    shadowElevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Scroll ke bawah",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkRed
                        )
                        Text(text = "👇", fontSize = 11.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // ================= 3. TYPOGRAPHY & VALUE PROPOSITION =================
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(text = "✨", fontSize = 14.sp)
                    Text(
                        text = "SOLUSI KULINER PINTAR",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkRed,
                        letterSpacing = 0.6.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Gak Pake Bingung\nMau Makan Apa!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextDark,
                    lineHeight = 36.sp,
                    letterSpacing = (-0.5).sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Temukan kuliner viral, resto terdekat, dan tanya rekomendasi langsung lewat Voice AI pintar.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = OpeningTextBrown,
                    lineHeight = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ================= 4. FEATURE HIGHLIGHTS =================
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                FeatureChipCard(
                    icon = Icons.Default.Mic,
                    iconBg = Color(0xFFFFDAD2),
                    iconTint = DarkRed,
                    title = "🎙️ Tanya Voice AI",
                    subtitle = "Ngomong \"Mau yang pedes manis\", AI langsung carikan"
                )
                FeatureChipCard(
                    icon = Icons.Default.Star,
                    iconBg = Color(0xFFFFDDB8),
                    iconTint = YellowAccent,
                    title = "🔥 Kuliner Viral & For You",
                    subtitle = "Update tren makanan yang lagi hits di kotamu"
                )
                FeatureChipCard(
                    icon = Icons.Default.LocationOn,
                    iconBg = Color(0xFF6FFBBE),
                    iconTint = GreenAccent,
                    title = "📍 Resto Terdekat & Navigasi",
                    subtitle = "Estimasi jalan kaki, antrean, dan promo aktif"
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // ================= 5. ACTION CTA BUTTONS =================
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Primary Button
                Button(
                    onClick = onMulaiEksplorClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(999.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Mulai Eksplor Rasa",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // Trust Micro-Copy
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🔒 50.000+ review makanan jujur dari foodies",
                        fontSize = 12.sp,
                        color = OpeningTextBrown
                    )
                }
            }
        }
    }
}

// ================= HEADER LOGO DENGAN ANIMASI GESER DARI TENGAH =================
@Composable
private fun AnimatedYumiHeader() {
    val yumiOffsetX = remember { Animatable(110f) }
    val umiOffsetX = remember { Animatable(-110f) }
    val umiAlpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        delay(600)

        launch {
            yumiOffsetX.animateTo(
                targetValue = 14f,
                animationSpec = tween(durationMillis = 700, easing = FastOutSlowInEasing)
            )
        }

        launch {
            umiOffsetX.animateTo(
                targetValue = -35f,
                animationSpec = tween(durationMillis = 700, easing = FastOutSlowInEasing)
            )
        }

        launch {
            umiAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 500)
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.wrapContentSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.yumi_mascot),
                contentDescription = "Yumi Mascot",
                modifier = Modifier
                    .height(130.dp)
                    .offset(x = yumiOffsetX.value.dp)
                    .zIndex(1f),
                contentScale = ContentScale.Fit
            )

            Image(
                painter = painterResource(id = R.drawable.umi),
                contentDescription = "UMI Graphic",
                modifier = Modifier
                    .height(105.dp)
                    .offset(x = umiOffsetX.value.dp)
                    .alpha(umiAlpha.value)
                    .zIndex(0f),
                contentScale = ContentScale.Fit
            )
        }
    }
}

// ================= BENTO FOOD COLLAGE (LOCAL DRAWABLE) =================
@Composable
private fun BentoFoodCollage() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1.3f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.nasi_padang),
                contentDescription = "Nasi Padang Hero",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                            startY = 100f
                        )
                    )
            )
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.BottomStart),
                shape = RoundedCornerShape(999.dp),
                color = Color.White.copy(alpha = 0.9f)
            ) {
                Text(
                    text = "🔥 Trending #1",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sate_ayam),
                    contentDescription = "Sate Ayam",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.9f))
                        .align(Alignment.TopEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = DarkRed,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pastry),
                    contentDescription = "Pastry",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Surface(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.BottomStart),
                    shape = RoundedCornerShape(999.dp),
                    color = Color.White.copy(alpha = 0.9f)
                ) {
                    Text(
                        text = "☕ Cafe Hits",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = GreenAccent,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

// ================= FEATURE CHIP CARD =================
@Composable
private fun FeatureChipCard(
    icon: ImageVector,
    iconBg: Color,
    iconTint: Color,
    title: String,
    subtitle: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = CardBg,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(20.dp)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextDark
                )
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = OpeningTextBrown
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun OpeningScreenPreview() {
    YumiTheme {
        OpeningScreen()
    }
}