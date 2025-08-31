package com.sdk.fly.ui.theme

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
    primary = SilverChrome80,
    onPrimary = CharcoalBlack40,
    primaryContainer = ChromeGrey80,
    onPrimaryContainer = CharcoalBlack80,

    secondary = ChromeGrey80,
    onSecondary = CharcoalBlack40,
    secondaryContainer = MetallicSilver80,
    onSecondaryContainer = CharcoalBlack80,

    tertiary = MetallicSilver80,
    onTertiary = CharcoalBlack40,
    tertiaryContainer = SilverChrome40,
    onTertiaryContainer = PureWhite80,

    background = CharcoalBlack80,
    onBackground = PureWhite80,
    surface = ModernGrey80,
    onSurface = PureWhite80,
    surfaceVariant = DeepCharcoal80,
    onSurfaceVariant = WhiteGrey80,

    error = ErrorRed,
    onError = PureWhite80,
    errorContainer = ErrorRedDark,
    onErrorContainer = ErrorRedLight,

    outline = SilverChrome40,
    outlineVariant = ChromeGrey80,
    scrim = CharcoalBlack40,
    inverseSurface = PureWhite80,
    inverseOnSurface = CharcoalBlack80,
    inversePrimary = CharcoalBlack40
)

private val LightColorScheme = lightColorScheme(
    primary = CharcoalBlack40,
    onPrimary = PureWhite80,
    primaryContainer = SilverChrome80,
    onPrimaryContainer = CharcoalBlack80,

    secondary = SilverChrome40,
    onSecondary = PureWhite80,
    secondaryContainer = ChromeGrey80,
    onSecondaryContainer = CharcoalBlack80,

    tertiary = ModernGrey80,
    onTertiary = PureWhite80,
    tertiaryContainer = MetallicSilver80,
    onTertiaryContainer = CharcoalBlack80,

    background = PureWhite80,
    onBackground = CharcoalBlack80,
    surface = CleanWhite80,
    onSurface = CharcoalBlack80,
    surfaceVariant = WhiteGrey80,
    onSurfaceVariant = ModernGrey80,

    error = ErrorRed,
    onError = PureWhite80,
    errorContainer = ErrorRedLight,
    onErrorContainer = ErrorRedDark,

    outline = SilverChrome40,
    outlineVariant = ChromeGrey80,
    scrim = CharcoalBlack40,
    inverseSurface = CharcoalBlack80,
    inverseOnSurface = PureWhite80,
    inversePrimary = SilverChrome80
)

@Composable
fun MyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
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


val MaterialTheme.successColor: androidx.compose.ui.graphics.Color
    @Composable get() = SuccessGreen

val MaterialTheme.warningColor: androidx.compose.ui.graphics.Color
    @Composable get() = WarningOrange

val MaterialTheme.infoColor: androidx.compose.ui.graphics.Color
    @Composable get() = InfoBlue

val MaterialTheme.onlineColor: androidx.compose.ui.graphics.Color
    @Composable get() = OnlineGreen

val MaterialTheme.pendingColor: androidx.compose.ui.graphics.Color
    @Composable get() = PendingYellow

val MaterialTheme.loadingColor: androidx.compose.ui.graphics.Color
    @Composable get() = LoadingBlue