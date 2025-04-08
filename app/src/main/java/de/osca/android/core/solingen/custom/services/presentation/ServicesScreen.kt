package de.osca.android.core.solingen.custom.services.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import de.osca.android.core.solingen.R
import de.osca.android.core.solingen.custom.services.presentation.components.ServiceElement
import de.osca.android.core.solingen.custom.services.widget.ServiceWidgetViewModel
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.design.RootContainer
import de.osca.android.essentials.presentation.component.screen_wrapper.ScreenWrapper
import de.osca.android.essentials.presentation.component.topbar.ScreenTopBar
import de.osca.android.essentials.utils.extensions.SetSystemStatusBar

/**
 *
 */
@Composable
fun ServicesScreen(
    navController: NavController,
    serviceWidgetViewModel: ServiceWidgetViewModel = hiltViewModel(),
    masterDesignArgs: MasterDesignArgs = serviceWidgetViewModel.defaultDesignArgs
) {
    val design = serviceWidgetViewModel.serviceDesignArgs

    LaunchedEffect(Unit) {
        serviceWidgetViewModel.initializeServices()
    }

    SetSystemStatusBar(
        !(design.mIsStatusBarWhite ?: masterDesignArgs.mIsStatusBarWhite), Color.Transparent
    )

    ScreenWrapper(
        topBar = {
            ScreenTopBar(
                title = stringResource(id = R.string.services_title),
                navController = navController,
                overrideBackgroundColor = design.mTopBarBackColor,
                overrideTextColor = design.mTopBarTextColor,
                masterDesignArgs = masterDesignArgs
            )
        },
        screenWrapperState = serviceWidgetViewModel.wrapperState,
        masterDesignArgs = masterDesignArgs,
        moduleDesignArgs = design
    ) {
        RootContainer(
            masterDesignArgs = masterDesignArgs,
            moduleDesignArgs = design
        ) {
            for(serviceItem in serviceWidgetViewModel.serviceData) {
                item {
                    ServiceElement(
                        titleText = serviceItem.title,
                        descriptionText = serviceItem.subTitle,
                        imageUrl = serviceItem.getIconUrl(),
                        isExternalLink = serviceItem.segueType == "http",
                        moduleDesignArgs = design,
                        externalLinkIconColorOverride = design.externalLinkIconColor,
                        externalLinkTextColorOverride = design.externalLinkTextColor,
                        onClick = {
                            if (serviceItem.segueType is String) {
                                navController.navigate(serviceItem.segueType as String)
                            } else {
                                navController.navigate(serviceItem.segueType)
                            }
                        },
                        masterDesignArgs = masterDesignArgs
                    )
                }
            }
        }
    }
}