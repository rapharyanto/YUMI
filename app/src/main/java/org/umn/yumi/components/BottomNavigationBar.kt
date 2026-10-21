
package org.umn.yumi.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.yumi.R
import org.umn.yumi.ui.theme.YumiCream
import org.umn.yumi.ui.theme.YumiRed

@Composable
fun BottomNavigationBar(
    selectedMenu: String = "jelajah",
    onMenuSelected: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            color = YumiRed,
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavItem(
                    iconRes = R.drawable.ic_navbar_beranda,
                    label = "Beranda",
                    isSelected = selectedMenu == "beranda",
                    onClick = { onMenuSelected("beranda") }
                )

                NavItem(
                    iconRes = R.drawable.ic_navbar_jelajah,
                    label = "Jelajah",
                    isSelected = selectedMenu == "jelajah",
                    onClick = { onMenuSelected("jelajah") }
                )

                Spacer(modifier = Modifier.width(56.dp))

                NavItem(
                    iconRes = R.drawable.ic_navbar_peta,
                    label = "Peta",
                    isSelected = selectedMenu == "peta",
                    onClick = { onMenuSelected("peta") }
                )

                NavItem(
                    iconRes = R.drawable.ic_navbar_profil,
                    label = "Profil",
                    isSelected = selectedMenu == "profil",
                    onClick = { onMenuSelected("profil") }
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-4).dp)
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(YumiRed)
                    .clickable { onMenuSelected("voice_ai") },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_navbar_mic
                    ),
                    contentDescription = "Voice AI",
                    colorFilter = ColorFilter.tint(YumiCream),
                    modifier = Modifier.size(26.dp)
                )
            }

            Text(
                text = "Voice AI",
                color = YumiCream,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun NavItem(
    iconRes: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            colorFilter = ColorFilter.tint(
                YumiCream.copy(
                    alpha = if (isSelected) 1f else 0.6f
                )
            ),
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = label,
            color = YumiCream.copy(
                alpha = if (isSelected) 1f else 0.6f
            ),
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(widthDp = 430, heightDp = 80)
@Composable
private fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}
