package org.umn.yumi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.umn.yumi.R
import org.umn.yumi.ui.theme.*

@Composable
fun MapLocation(
    onBackClicked: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(YumiCream)
    ) {
        Image(
            painter = painterResource(id = R.drawable.rute_bakso_solo),
            contentDescription = "Peta Gading Serpong",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Surface(
            shape = CircleShape,
            color = YumiCream,
            shadowElevation = 4.dp,
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp)
                .align(Alignment.TopStart)
                .clickable { onBackClicked() }
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "←",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = YumiRed
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 412, heightDp = 800)
@Composable
private fun MapLocationPreview() {
    YumiTheme {
        MapLocation(
            onBackClicked = {}
        )
    }
}