package de.osca.android.core.solingen.custom.settings.presentation.imprint

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
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
@Composable
fun ImprintScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    masterDesignArgs: MasterDesignArgs = settingsViewModel.defaultDesignArgs
) {
    val design = settingsViewModel.settingsDesignArgs

    LaunchedEffect(Unit) {
        settingsViewModel.initializeServerConfig()
    }

    ScreenWrapper(
        topBar = {
            ScreenTopBar(
                title = stringResource(id = R.string.imprint_title),
                navController = navController,
                overrideBackgroundColor = design.mTopBarBackColor,
                overrideTextColor = design.mTopBarTextColor,
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
                        htmlString = settingsViewModel.getImprint()
                    )
                }
            }
        }
    }
}