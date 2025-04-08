package de.osca.android.core.solingen.custom.settings.presentation.dataPrivacy

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import de.osca.android.core.solingen.R
import de.osca.android.core.solingen.custom.settings.presentation.SettingsViewModel
import de.osca.android.essentials.presentation.component.design.BaseCardContainer
import de.osca.android.essentials.presentation.component.design.HtmlTextWebview
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.design.RootContainer
import de.osca.android.essentials.presentation.component.screen_wrapper.ScreenWrapper
import de.osca.android.essentials.presentation.component.topbar.ScreenTopBar

/**
 *
 */
@SuppressLint("UnrememberedMutableState")
@Composable
fun DataPrivacyScreen(
    settingsViewModel: SettingsViewModel,
    navController: NavController,
    masterDesignArgs: MasterDesignArgs
) {
    val design = settingsViewModel.settingsDesignArgs

    LaunchedEffect(Unit) {
        settingsViewModel.initializeServerConfig()
    }

    ScreenWrapper(
        topBar = {
            ScreenTopBar(
                title = stringResource(id = R.string.global_data_privacy),
                navController = navController,
                masterDesignArgs = masterDesignArgs
            )
        },
        screenWrapperState = settingsViewModel.wrapperState,
        masterDesignArgs = masterDesignArgs,
        moduleDesignArgs = design
    ) {
        RootContainer(
            masterDesignArgs = masterDesignArgs,
            moduleDesignArgs = design
        ) {
            item {
                BaseCardContainer(
                    masterDesignArgs = masterDesignArgs,
                    moduleDesignArgs = design
                ) {
                    HtmlTextWebview(
                        htmlString = settingsViewModel.getDataPrivacy()
                    )
                }
            }
        }
    }
}