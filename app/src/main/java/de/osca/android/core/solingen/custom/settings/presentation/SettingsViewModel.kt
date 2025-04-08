package de.osca.android.core.solingen.custom.settings.presentation

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.parse.ParseInstallation
import dagger.hilt.android.lifecycle.HiltViewModel
import de.osca.android.core.solingen.Login
import de.osca.android.core.solingen.R
import de.osca.android.core.solingen.custom.settings.presentation.args.SettingsDesignArgs
import de.osca.android.essentials.data.remote.EssentialsApiService
import de.osca.android.essentials.domain.entity.SaveFileObject
import de.osca.android.essentials.domain.entity.push_notification.NotificationChannel
import de.osca.android.essentials.domain.entity.server.ServerConfig
import de.osca.android.essentials.presentation.base.BaseViewModel
import de.osca.android.essentials.utils.extensions.displayContent
import de.osca.android.networkservice.utils.RequestHandler
import de.osca.android.waste.domain.boundaries.WasteRepository
import de.osca.android.weather.domain.boundaries.WeatherRepository
import de.osca.android.weather.domain.entity.WeatherObserved
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    val settingsDesignArgs: SettingsDesignArgs,
    private val essentialsApiService: EssentialsApiService,
    private val requestHandler: RequestHandler,
    private val wasteRepository: WasteRepository,
    private val weatherRepository: WeatherRepository
) : BaseViewModel() {
    private val serverConfig = mutableStateOf<ServerConfig?>(null)
    val isWasteNotificationEnabled = mutableStateOf<Boolean>(false)
    val isShowWasteDashboardEnabled = mutableStateOf<Boolean>(false)
    val isWasteAddressSaved = mutableStateOf<Boolean>(false)
    val myWeatherStation = mutableStateOf<WeatherObserved?>(null)

    var saveFileObject = SaveFileObject()
    val installation: MutableState<ParseInstallation?> = mutableStateOf(null)

    fun initializeAll() {
        viewModelScope.launch {
            async {
                getIsWasteNotificationEnabled()
                getIsShowWasteDashboard()
                isWasteAddressSaved()
                fetchInstallation()
                fetchServerConfig()
                getWeatherStation()
            }
        }
    }

    fun initializeServerConfig() {
        viewModelScope.launch {
            async {
                fetchServerConfig()
            }
        }
    }

    fun getWeatherStation() = launchDataLoad {
        if (weatherRepository.getWeatherStationId() != null){
            myWeatherStation.value =  weatherRepository.getMyWeather()
        }
    }
    fun fetchInstallation() {
        installation.value = Login.getInstallation()
    }

    fun fetchServerConfig(): Job = launchDataLoad {
        serverConfig.value = requestHandler.makeRequest(essentialsApiService::getConfig)

        wrapperState.displayContent()
    }

    fun getDataPrivacy(): String {
        return serverConfig.value?.params?.privacyText ?: ""
    }

    fun getImprint(): String {
        return serverConfig.value?.params?.imprintText ?: ""
    }

    fun changeNotificationSubscription(channel: NotificationChannel, subscribe: Boolean) = launchDataLoad {
        Login.updateChannels(channel, subscribe)
    }

    fun onSwitchWasteNotification(context: Context, enable: Boolean) = launchDataLoad {
        wasteRepository.setWasteNotificationEnabled(enable)
    }

    fun getIsWasteNotificationEnabled() = launchDataLoad {
        isWasteNotificationEnabled.value = wasteRepository.isWasteNotificationEnabled()
    }

    fun onSwitchShowWasteDashboard(enable: Boolean) = launchDataLoad {
        wasteRepository.setShowWasteDashboardEnabled(enable)
    }

    fun getIsShowWasteDashboard() = launchDataLoad {
        isShowWasteDashboardEnabled.value = wasteRepository.isShowWasteDashboardEnabled()
    }

    fun isWasteAddressSaved() = launchDataLoad {
        isWasteAddressSaved.value = if(wasteRepository.getSelectedWasteAddress() != null) true else false
    }

    /**
     * Create Notification Channel for Waste
     */
    private fun createNotificationChannel(context: Context, channelId: String, nameText: String, descriptionText: String) {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = android.app.NotificationChannel(channelId, nameText, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}