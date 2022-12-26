package app.delish.compose.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import app.delish.common.compose.R

@ExperimentalTextApi
internal fun createSingleGoogleFontFamily(
    name: String,
    provider: GoogleFont.Provider = GmsFontProvider,
    weights: List<FontWeight>
): FontFamily = FontFamily(
    weights.map { weight ->
        androidx.compose.ui.text.googlefonts.Font(
            googleFont = GoogleFont(name),
            fontProvider = provider,
            weight = weight
        )
    }
)

@ExperimentalTextApi
internal val GmsFontProvider: GoogleFont.Provider by lazy {
    GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
}

@OptIn(ExperimentalTextApi::class)
val DelishTypography = Typography(
    defaultFontFamily = createSingleGoogleFontFamily(
        name = "Inter",
        weights = listOf(
            FontWeight.Light,
            FontWeight.Normal,
            FontWeight.Medium,
            FontWeight.Bold
        )
    )
)
