package de.osca.android.core.solingen.custom.townhall.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import de.osca.android.core.solingen.custom.townhall.navigation.TownhallNavItems
import de.osca.android.core.solingen.custom.townhall.presentation.TownhallViewModel
import de.osca.android.core.solingen.custom.townhall.presentation.components.TownhallInfoElement
import de.osca.android.essentials.presentation.component.design.*
import de.osca.android.essentials.utils.extensions.safeTake

/**
 *
 */
@Composable
fun TownhallWidget(
    navController: NavController,
    townhallViewModel: TownhallViewModel = hiltViewModel(),
    masterDesignArgs: MasterDesignArgs = townhallViewModel.defaultDesignArgs
) {
    if(townhallViewModel.townhallDesignArgs.vIsWidgetVisible) {
        val design = townhallViewModel.townhallDesignArgs

        LaunchedEffect(Unit) {
            townhallViewModel.initializeTownhall()
        }

        BaseListContainer(
            text = stringResource(id = design.vWidgetTitle),
            showMoreOption = design.vWidgetShowMoreOption,
            moduleDesignArgs = design,
            onMoreOptionClick = {
                navController.navigate(TownhallNavItems.TownhallNavItem.route)
            },
            masterDesignArgs = masterDesignArgs
        ) {
            MultiColumnList(
                columnCount = design.columnCount
            ) {
                townhallViewModel.townhallItems.safeTake(design.columnCount).map { item ->
                    { modifier ->
                        TownhallInfoElement(
                            masterDesignArgs = masterDesignArgs,
                            moduleDesignArgs = design,
                            title = item.title ?: "",
                            iconUrl = item.getIconUrl(),
                            useDummies = false,
                            showTownhallIcon = design.showTownhallIcon,
                            showArrowIcon = design.showArrowIcon,
                            onClick = {
                                navController.navigate(item.segueType)
                            },
                            modifier = modifier
                        )
                    }
                }
            }
        }
    }
}