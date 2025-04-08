package de.osca.android.core.solingen.custom.splash

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.osca.android.core.solingen.BuildConfig
import de.osca.android.core.solingen.Login
import de.osca.android.core.solingen.OSCAProperties
import de.osca.android.core.solingen.R
import de.osca.android.core.solingen.custom.splash.args.SplashDesignArgs
import de.osca.android.district.core.DistrictModule
import de.osca.android.district.core.data.boundaries.ParseObjectRegistration
import de.osca.android.environment.domain.boundaries.EnvironmentParseRegistration
import de.osca.android.essentials.domain.boundaries.EssentialsStorage
import de.osca.android.essentials.presentation.base.BaseViewModel
import de.osca.android.essentials.presentation.component.screen_wrapper.ScreenWrapperState
import de.osca.android.essentials.utils.extensions.displayContent
import de.osca.android.essentials.utils.extensions.failure
import de.osca.android.essentials.utils.extensions.isNetworkAvailable
import de.osca.android.essentials.utils.strings.EssentialsStrings
import de.osca.android.networkservice.interceptor.AuthInterceptor
import de.osca.android.waste.domain.misc.WasteHelper
import io.sentry.Sentry
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject
constructor(
    private val strings: EssentialsStrings,
    private val essentialsStorage: EssentialsStorage,
    val splashDesignArgs: SplashDesignArgs,
    private val environmentParseRegistration: EnvironmentParseRegistration,
    private var parseRegistrations: Set<@JvmSuppressWildcards ParseObjectRegistration>,
) : BaseViewModel() {
    var isFirstStart by mutableStateOf(true)
    var showSplashScreen = MutableLiveData(true)

    fun initialize(context: Context) =
        viewModelScope.launch {
            wrapperState.value = ScreenWrapperState.Loading
            DistrictModule.politicsURL = BuildConfig.POLITICS_URL
            WasteHelper.parseBaseUrl = BuildConfig.PARSE_URL
            if (context.isNetworkAvailable()) {
                isFirstStart = essentialsStorage.getIsFirstStart()
                println("LOGINSPLASH")
                login(context)
            } else {
                wrapperState.failure(strings.getString(R.string.global_no_internet))
                showSplashScreen.value = false
            }
        }

    private fun login(context: Context) {
        Login.anonymousLogIn(
            context = context,
            isLoggedIn = {
                AuthInterceptor.sessionToken = it.sessionToken
                wrapperState.displayContent()
                showSplashScreen.value = false
            },
            isError = {
                Log.e(this.javaClass.name, it.message, it)
                wrapperState.failure(strings.getString(R.string.global_try_later))
                showSplashScreen.value = false
                // TODO: check if check is required
                if (OSCAProperties.isSentryEnabled) {
                    Sentry.captureException(it)
                }
            },
            environmentParseRegistration = environmentParseRegistration,
            parseRegistrations = parseRegistrations,
        )
    }
}
