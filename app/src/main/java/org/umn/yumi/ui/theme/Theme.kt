
package org.umn.yumi.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = YumiRed,
    secondary = YumiDarkRed,
    tertiary = YumiMutedText,
    background = YumiText,
    surface = YumiText,
    onPrimary = YumiWhite,
    onSecondary = YumiWhite,
    onBackground = YumiCream,
    onSurface = YumiCream
)

private val LightColorScheme = lightColorScheme(
    primary = YumiRed,
    secondary = YumiDarkRed,
    tertiary = YumiMutedText,
    background = YumiCream,
    surface = YumiWhite,
    onPrimary = YumiWhite,
    onSecondary = YumiWhite,
    onBackground = YumiText,
    onSurface = YumiText,
    outline = YumiBorder
)

@Composable
fun YumiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
