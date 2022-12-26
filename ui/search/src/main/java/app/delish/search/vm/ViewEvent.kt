package app.delish.search.vm

import app.delish.base.vm.MviViewModel

internal sealed interface ViewEvent : MviViewModel.MviEvent {
    data class SearchQuery(val query: String?, val cuisine: String?) : ViewEvent
}
