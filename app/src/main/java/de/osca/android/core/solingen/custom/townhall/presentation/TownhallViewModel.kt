package de.osca.android.core.solingen.custom.townhall.presentation

import androidx.compose.runtime.mutableStateListOf
import dagger.hilt.android.lifecycle.HiltViewModel
import de.osca.android.core.solingen.custom.townhall.domain.TownhallData
import de.osca.android.core.solingen.custom.townhall.data.TownhallApiService
import de.osca.android.core.solingen.custom.townhall.presentation.args.TownhallDesignArgs
import de.osca.android.essentials.presentation.base.BaseViewModel
import de.osca.android.essentials.utils.extensions.displayContent
import de.osca.android.essentials.utils.extensions.loading
import de.osca.android.essentials.utils.extensions.resetWith
import de.osca.android.networkservice.utils.RequestHandler
import javax.inject.Inject

@HiltViewModel
class TownhallViewModel @Inject constructor(
    val townhallDesignArgs: TownhallDesignArgs,
    private val townhallApiService: TownhallApiService,
    private val requestHandler: RequestHandler
) : BaseViewModel() {
    val townhallItems = mutableStateListOf<TownhallData>()

    fun initializeTownhall() {
        wrapperState.loading()
        getTownhallItems()
    }

    private fun getTownhallItems() = launchDataLoad {
        val result = requestHandler.makeRequest(townhallApiService::getTownhallData) ?: emptyList()
        val toDisplay = result.filter { it.isAvailable }.sortedBy { it.position }
        townhallItems.resetWith(toDisplay)

        wrapperState.displayContent()
    }
}