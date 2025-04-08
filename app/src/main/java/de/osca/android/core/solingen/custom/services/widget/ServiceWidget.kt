package de.osca.android.core.solingen.custom.services.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import de.osca.android.core.solingen.custom.services.navigation.ServicesNavItems
import de.osca.android.core.solingen.custom.services.presentation.components.ServiceElement
import de.osca.android.essentials.presentation.component.design.BaseListContainer
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.design.SimpleSpacedList
import de.osca.android.essentials.utils.extensions.safeTake

/**
 *
 */
@Composable
fun ServiceWidget(
    navController: NavController,
    serviceWidgetViewModel: ServiceWidgetViewModel = hiltViewModel(),
    masterDesignArgs: MasterDesignArgs = serviceWidgetViewModel.defaultDesignArgs,
) {
    if(serviceWidgetViewModel.serviceDesignArgs.vIsWidgetVisible) {
        val design = serviceWidgetViewModel.serviceDesignArgs

        LaunchedEffect(Unit) {
            serviceWidgetViewModel.initializeServices()
        }

        BaseListContainer(
            text = stringResource(id = design.vWidgetTitle),
            showMoreOption = design.vWidgetShowMoreOption,
            moduleDesignArgs = design,
            onMoreOptionClick = {
                navController.navigate(ServicesNavItems.ServicesNavItem.route)
            },
            masterDesignArgs = masterDesignArgs
        ) {
            SimpleSpacedList(
                masterDesignArgs = masterDesignArgs
            ) {
                serviceWidgetViewModel.serviceData.safeTake(design.previewCountForWidget).forEach { serviceItem ->
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