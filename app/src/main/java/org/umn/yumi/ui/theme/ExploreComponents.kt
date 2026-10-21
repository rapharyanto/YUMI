package org.umn.yumi.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val ComponentRed = Color(0xFFA83219)
private val ComponentLavender = Color(0xFFF2EAFB)
private val ComponentText = Color(0xFF25232A)
private val ComponentMuted = Color(0xFF817A86)

@Composable
fun ExploreSectionLabel(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = title,
            color = ComponentRed,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = subtitle,
            color = ComponentMuted,
            fontSize = 13.sp,
            lineHeight = 19.sp
        )
    }
}

@Composable
fun ExploreLocationChip(
    location: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(22.dp),
        color = ComponentLavender
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 9.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "⌖",
                color = ComponentRed,
                fontSize = 16.sp
            )

            Spacer(Modifier.width(5.dp))

            Text(
                text = location,
                color = ComponentText,
                fontSize = 11.sp
            )

            Spacer(Modifier.width(5.dp))

            Text(
                text = "⌄",
                color = ComponentMuted,
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun ExploreStatusChip(
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFFF0EBF4)
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 5.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier
                    .size(6.dp)
                    .background(
                        Color(0xFF7BBE9C),
                        CircleShape
                    )
            )

            Spacer(Modifier.width(5.dp))

            Text(
                text = label,
                color = ComponentText,
                fontSize = 9.sp
            )
        }
    }
}

@Composable
fun ExploreActionChip(
    label: String,
    icon: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(22.dp),
        color = if (selected) {
            ComponentRed
        } else {
            ComponentLavender
        }
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 13.dp,
                vertical = 9.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = icon,
                color = if (selected) Color.White else ComponentRed,
                fontSize = 12.sp
            )

            Spacer(Modifier.width(5.dp))

            Text(
                text = label,
                color = if (selected) Color.White else ComponentText,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ExploreRatingChip(
    label: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(6.dp),
        color = backgroundColor
    ) {
        Text(
            text = label,
            color = textColor,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 5.dp
            )
        )
    }
}
