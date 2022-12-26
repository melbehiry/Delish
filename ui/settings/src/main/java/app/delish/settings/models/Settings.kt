package app.delish.settings.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import app.delish.settings.R

sealed class Settings(
    @DrawableRes val icon: Int,
    @StringRes val name: Int,
    @StringRes val value: Int? = null
) {

    data class Language(
        val selectedLanguage: Int
    ): Settings(
        icon = R.drawable.ic_settings_language,
        name = R.string.settings_item_language
    )

    object ShareApp: Settings(
        icon = R.drawable.ic_settings_share_app,
        name = R.string.settings_item_share_app
    )

    object AskQuestion: Settings(
        icon = R.drawable.ic_settings_ask_question,
        name = R.string.settings_item_ask_question
    )
}
