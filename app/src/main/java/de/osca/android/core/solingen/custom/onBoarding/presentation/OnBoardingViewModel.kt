package de.osca.android.core.solingen.custom.onBoarding.presentation

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import de.osca.android.core.solingen.custom.onBoarding.presentation.args.OnBoardingDesignArgs
import de.osca.android.essentials.data.remote.EssentialsApiService
import de.osca.android.essentials.domain.boundaries.EssentialsStorage
import de.osca.android.essentials.domain.entity.SaveFileObject
import de.osca.android.essentials.domain.entity.server.ServerConfig
import de.osca.android.essentials.presentation.base.BaseViewModel
import de.osca.android.essentials.utils.extensions.displayContent
import de.osca.android.essentials.utils.extensions.loading
import de.osca.android.networkservice.utils.RequestHandler
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    val onBoardingDesignArgs: OnBoardingDesignArgs,
    private val essentialsApiService: EssentialsApiService,
    private val requestHandler: RequestHandler,
    private val essentialsStorage: EssentialsStorage
) : BaseViewModel() {
    var saveFileObject = SaveFileObject()
    private val serverConfig = mutableStateOf<ServerConfig?>(null)

    fun initializeServerConfig() {
        wrapperState.loading()
        getServerConfig()
    }

    private fun getServerConfig() = launchDataLoad {
        serverConfig.value = requestHandler.makeRequest(essentialsApiService::getConfig)

        wrapperState.displayContent()
    }

    fun getDataPrivacy(): String {
        if (serverConfig.value == null) {
            getServerConfig()
        }
        return serverConfig.value?.params?.privacyText.toString()
    }

    fun setIsFirstStart(value: Boolean) {
        launchDataLoad {
            essentialsStorage.saveIsFirstStart(value)
        }
    }
}