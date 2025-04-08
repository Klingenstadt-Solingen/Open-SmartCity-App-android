package de.osca.android.core.solingen.custom.townhall.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import de.osca.android.core.solingen.R
import de.osca.android.core.solingen.custom.townhall.presentation.components.TownhallInfoElement
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.design.MultiColumnList
import de.osca.android.essentials.presentation.component.design.RootContainer
import de.osca.android.essentials.presentation.component.screen_wrapper.ScreenWrapper
import de.osca.android.essentials.presentation.component.topbar.ScreenTopBar
import de.osca.android.essentials.utils.extensions.SetSystemStatusBar

/**
 *
 */
@Composable
fun TownhallScreen(
    navController: NavController,
    townhallViewModel: TownhallViewModel = hiltViewModel(),
    masterDesignArgs: MasterDesignArgs = townhallViewModel.defaultDesignArgs
) {
    val design = townhallViewModel.townhallDesignArgs

    LaunchedEffect(Unit) {
        townhallViewModel.initializeTownhall()
    }

    SetSystemStatusBar(
        !(design.mIsStatusBarWhite ?: masterDesignArgs.mIsStatusBarWhite), Color.Transparent
    )

    ScreenWrapper(
        topBar = {
            ScreenTopBar(
                title = stringResource(id = R.string.townhall_title),
                navController = navController,
                overrideBackgroundColor = design.mTopBarBackColor,
                overrideTextColor = design.mTopBarTextColor,
                masterDesignArgs = masterDesignArgs
            )
        },
        screenWrapperState = townhallViewModel.wrapperState,
        masterDesignArgs = masterDesignArgs,
        moduleDesignArgs = design
    ) {
        RootContainer(
            masterDesignArgs = masterDesignArgs,
            moduleDesignArgs = design
        ) {
            item {
                MultiColumnList(
                    columnCount = design.columnCount
                ) {
                    townhallViewModel.townhallItems.map { item ->
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
}