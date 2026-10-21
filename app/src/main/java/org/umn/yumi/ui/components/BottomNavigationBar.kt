package org.umn.yumi.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.yumi.R
import org.umn.yumi.ui.theme.Krim
import org.umn.yumi.ui.theme.MerahUtama
import org.umn.yumi.ui.theme.OranyeKuat

// Ukuran-ukuran navbar (diatur di satu tempat supaya mudah disesuaikan)
private val TinggiTotal = 88.dp          // tinggi seluruh area (bar + tombol yang menonjol)
private val TinggiBar = 64.dp            // tinggi bar merah
private val UkuranTombolVoice = 56.dp    // diameter tombol Voice AI (termasuk cincin krim)
private val JariJariLekukan = 31.dp      // jari-jari lekukan bulat di bar
private val PusatLekukanY = 4.dp         // posisi pusat lekukan dari tepi atas bar

/**
 * Bentuk bar: sudut atas membulat + lekukan setengah lingkaran di tengah atas,
 * tempat tombol Voice AI "duduk".
 */
private class BentukNavbarLekukan(
    private val radiusSudut: Dp,
    private val radiusLekukan: Dp,
    private val pusatLekukanY: Dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val sudut = with(density) { radiusSudut.toPx() }
        val rLekukan = with(density) { radiusLekukan.toPx() }
        val cy = with(density) { pusatLekukanY.toPx() }

        val bar = Path().apply {
            addRoundRect(
                RoundRect(
                    left = 0f,
                    top = 0f,
                    right = size.width,
                    bottom = size.height,
                    topLeftCornerRadius = CornerRadius(sudut),
                    topRightCornerRadius = CornerRadius(sudut),
                    bottomLeftCornerRadius = CornerRadius.Zero,
                    bottomRightCornerRadius = CornerRadius.Zero
                )
            )
        }

        val lekukan = Path().apply {
            addOval(Rect(center = Offset(size.width / 2f, cy), radius = rLekukan))
        }

        val hasil = Path.combine(PathOperation.Difference, bar, lekukan)
        return Outline.Generic(hasil)
    }
}

@Composable
fun BottomNavigationBar(
    selectedMenu: String = "beranda",
    onMenuSelected: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(TinggiTotal)
    ) {
        // Bar merah dengan lekukan di tengah atas
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(TinggiBar)
                .align(Alignment.BottomCenter),
            color = MerahUtama,
            shape = BentukNavbarLekukan(
                radiusSudut = 24.dp,
                radiusLekukan = JariJariLekukan,
                pusatLekukanY = PusatLekukanY
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp, end = 8.dp, top = 6.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavItem(
                    iconRes = R.drawable.ic_navbar_beranda,
                    label = "Beranda",
                    isSelected = selectedMenu == "beranda",
                    onClick = { onMenuSelected("beranda") },
                    modifier = Modifier.weight(1f)
                )

                NavItem(
                    iconRes = R.drawable.ic_navbar_jelajah,
                    label = "Jelajah",
                    isSelected = selectedMenu == "jelajah",
                    onClick = { onMenuSelected("jelajah") },
                    modifier = Modifier.weight(1f)
                )

                // Ruang kosong untuk tombol Voice AI di tengah
                Spacer(modifier = Modifier.width(76.dp))

                NavItem(
                    iconRes = R.drawable.ic_navbar_peta,
                    label = "Peta",
                    isSelected = selectedMenu == "peta",
                    onClick = { onMenuSelected("peta") },
                    modifier = Modifier.weight(1f)
                )

                NavItem(
                    iconRes = R.drawable.ic_navbar_profil,
                    label = "Profil",
                    isSelected = selectedMenu == "profil",
                    onClick = { onMenuSelected("profil") },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Tombol Voice AI: lingkaran oranye dengan cincin krim, menonjol di atas bar
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(UkuranTombolVoice)
                .clip(CircleShape)
                .background(OranyeKuat)
                .border(width = 3.dp, color = Krim, shape = CircleShape)
                .clickable { onMenuSelected("voice_ai") },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_navbar_mic),
                contentDescription = "Voice AI",
                colorFilter = ColorFilter.tint(Krim),
                modifier = Modifier.size(26.dp)
            )
        }

        // Label "Voice AI" berada di dalam bar merah, di bawah lekukan
        Text(
            text = "Voice AI",
            color = Krim,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = if (selectedMenu == "voice_ai") FontWeight.Bold else FontWeight.SemiBold
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
        )
    }
}

@Composable
private fun NavItem(
    iconRes: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(vertical = 4.dp, horizontal = 2.dp)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            colorFilter = ColorFilter.tint(
                if (isSelected) Krim else Krim.copy(alpha = 0.8f)
            ),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = label,
            color = Krim,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold
            )
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 430,
    heightDp = 88
)
@Composable
private fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}