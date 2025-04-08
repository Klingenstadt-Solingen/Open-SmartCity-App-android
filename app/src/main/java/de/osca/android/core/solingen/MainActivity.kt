package de.osca.android.core.solingen

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.animation.doOnEnd
import androidx.core.net.toUri
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.util.Consumer
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessagingService
import dagger.hilt.android.AndroidEntryPoint
import de.osca.android.core.solingen.custom.splash.SplashErrorScreen
import de.osca.android.core.solingen.custom.splash.SplashViewModel
import de.osca.android.core.solingen.ui.theme.OSCACoreTheme
import de.osca.android.essentials.presentation.MainActivitySharedViewModel
import de.osca.android.networkservice.messaging.MessagingService.Companion.NAMED_DEEPLINK_DIRECTION_EXTRA
import java.util.Locale

const val IS_MOCKED = false
var USE_WIDGETS = false
var FORCE_LOCALE: Locale? = null
var isDarkTheme = false

@ExperimentalComposeUiApi
@AndroidEntryPoint
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPermissionsApi
class MainActivity : AppCompatActivity() {
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setOnExitAnimationListener { splashScreenView ->
                ObjectAnimator
                    .ofFloat(
                        splashScreenView.view,
                        View.ALPHA,
                        1f,
                        0f,
                    ).apply {
                        duration = 500L
                        doOnEnd { splashScreenView.remove() }
                        start()
                    }
            }
            setKeepOnScreenCondition {
                splashViewModel.showSplashScreen.value != false
            }
        }

        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        println("splashviewmodl")
        splashViewModel.initialize(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        changeDeeplinkInIntent(intent)
        showContent()
    }

    private fun changeDeeplinkInIntent(intent: Intent): Intent {
        /**
         * Handle Deeplink on cold start from Firebase Notification without the need of FirebaseMessagingService
         *
         * @see FirebaseMessagingService
         */
        val deeplink = intent.getStringExtra(NAMED_DEEPLINK_DIRECTION_EXTRA)
        if (intent.data == null && !deeplink.isNullOrEmpty()) {
            intent.data = deeplink.toUri()
        }
        return intent
    }

    private fun showContent() {
        /**
         * Wrapper for entire Application
         */
        setContent {
            OSCACoreTheme {
                SplashErrorScreen {
                    val sharedViewModel = hiltViewModel<MainActivitySharedViewModel>()
                    sharedViewModel.setIsBottomNavVisible(false)

                    val navController = rememberNavController()

                    DisposableEffect(Unit) {
                        val listener =
                            Consumer<Intent> {
                                navController.handleDeepLink(changeDeeplinkInIntent(it))
                            }
                        addOnNewIntentListener(listener)
                        onDispose { removeOnNewIntentListener(listener) }
                    }

                    CityApp(
                        sharedViewModel = sharedViewModel,
                        masterDesignArgs = sharedViewModel.defaultDesignArgs,
                        navController = navController,
                    )
                }
            }
        }
    }
}
