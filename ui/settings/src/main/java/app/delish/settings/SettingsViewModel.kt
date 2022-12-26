package app.delish.settings

import androidx.lifecycle.ViewModel
import app.delish.settings.models.Settings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class SettingsViewModel @Inject constructor() : ViewModel() {

    fun getSettings(): List<List<Settings>> {
        return listOfNotNull(
            listOf(
                Settings.Language(0)
            ),
            listOf(
                Settings.ShareApp,
                Settings.AskQuestion
            )
        )
    }
}
