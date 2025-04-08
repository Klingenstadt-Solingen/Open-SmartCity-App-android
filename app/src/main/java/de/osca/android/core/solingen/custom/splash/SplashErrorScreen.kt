package de.osca.android.core.solingen.custom.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import de.osca.android.core.solingen.custom.splash.args.SplashDesignArgs
import de.osca.android.essentials.R
import de.osca.android.essentials.presentation.component.design.LoadingScreen
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.design.MessageBoxScreen
import de.osca.android.essentials.presentation.component.screen_wrapper.ScreenWrapperState
import de.osca.android.essentials.utils.extensions.SetSystemStatusBar

@Composable
fun SplashErrorScreen(
    splashViewModel: SplashViewModel = hiltViewModel(),
    masterDesignArgs: MasterDesignArgs = splashViewModel.defaultDesignArgs,
    moduleDesignArgs: SplashDesignArgs = splashViewModel.splashDesignArgs,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current

    when (splashViewModel.wrapperState.value) {
        is ScreenWrapperState.Failure -> {
            SetSystemStatusBar(
                !(moduleDesignArgs.mIsStatusBarWhite ?: masterDesignArgs.mIsStatusBarWhite),
                Color.Transparent,
            )

            MessageBoxScreen(
                message = splashViewModel.wrapperState.value.msg,
                masterDesignArgs = masterDesignArgs,
                moduleDesignArgs = moduleDesignArgs,
                action = {
                    splashViewModel.initialize(context)
                },
                actionName = stringResource(id = R.string.global_try_again),
            )
        }

        is ScreenWrapperState.DisplayContent -> {
            content()
        }

        is ScreenWrapperState.Loading, ScreenWrapperState.WaitingInitialization -> {
            LoadingScreen(
                masterDesignArgs = masterDesignArgs,
                moduleDesignArgs = moduleDesignArgs,
            )
        }
    }
}
