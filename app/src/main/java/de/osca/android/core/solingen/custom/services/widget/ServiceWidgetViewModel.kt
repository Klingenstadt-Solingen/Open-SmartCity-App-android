package de.osca.android.core.solingen.custom.services.widget

import androidx.compose.runtime.mutableStateListOf
import dagger.hilt.android.lifecycle.HiltViewModel
import de.osca.android.core.solingen.custom.services.domain.ServiceData
import de.osca.android.core.solingen.custom.services.data.ServiceApiService
import de.osca.android.core.solingen.custom.services.presentation.args.ServiceDesignArgs
import de.osca.android.essentials.presentation.base.BaseViewModel
import de.osca.android.essentials.utils.extensions.displayContent
import de.osca.android.essentials.utils.extensions.resetWith
import de.osca.android.networkservice.utils.RequestHandler
import javax.inject.Inject

@HiltViewModel
class ServiceWidgetViewModel @Inject constructor(
    val serviceDesignArgs: ServiceDesignArgs,
    private val serviceApiService: ServiceApiService,
    private val requestHandler: RequestHandler
) : BaseViewModel() {
    val serviceData = mutableStateListOf<ServiceData>()

    fun initializeServices() {
        fetchServiceData()
    }

    fun fetchServiceData() = launchDataLoad {
        val result = requestHandler.makeRequest(serviceApiService::getServiceData) ?: emptyList()
        serviceData.resetWith(result.filter { it.isAvailable }.sortedBy { it.position })

        wrapperState.displayContent()
    }
}