package de.osca.android.core.solingen.custom.dashboard.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import de.osca.android.core.solingen.IS_MOCKED
import de.osca.android.core.solingen.Login
import de.osca.android.core.solingen.OSCAProperties
import de.osca.android.core.solingen.R
import de.osca.android.core.solingen.USE_WIDGETS
import de.osca.android.core.solingen.custom.dashboard.entity.WidgetModules
import de.osca.android.core.solingen.custom.services.widget.ServiceWidget
import de.osca.android.core.solingen.custom.townhall.widget.TownhallWidget
import de.osca.android.district.core.viewmodel.DistrictViewModel
import de.osca.android.district.core.viewmodel.sharedHiltViewModel
import de.osca.android.district.widget.DistrictEventWidget
import de.osca.android.district.widget.DistrictWidget
import de.osca.android.environment.widget.EnvironmentWidget
import de.osca.android.essentials.presentation.MainActivitySharedViewModel
import de.osca.android.essentials.presentation.component.design.HeaderImageCard
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.design.RootContainer
import de.osca.android.essentials.presentation.component.design.WidgetContainer
import de.osca.android.essentials.presentation.component.screen_wrapper.ScreenWrapper
import de.osca.android.essentials.utils.extensions.SetSystemStatusBar
import de.osca.android.essentials.utils.extensions.getLastDeviceLocation
import de.osca.android.essentials.utils.extensions.shortToast
import de.osca.android.jobs.widget.JobWidget
import de.osca.android.map.widget.MapWidget
import de.osca.android.press_release.widget.PressReleaseWidget
import de.osca.android.waste.widget.WasteWidget
import de.osca.android.waste.widget.WasteWidgetCalendar
import de.osca.android.weather.widget.WeatherWidget
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun DashboardScreen(
    sharedViewModel: MainActivitySharedViewModel,
    navController: NavController,
    initialLocation: LatLng,
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
    masterDesignArgs: MasterDesignArgs = dashboardViewModel.defaultDesignArgs,
) {
    val context = LocalContext.current
    val design = dashboardViewModel.dashboardDesignArgs

    val dashboardWidgetList = remember { dashboardViewModel.dashboardWidgets }
    val location = remember { mutableStateOf(initialLocation) }

    LaunchedEffect(Unit) {
        dashboardViewModel.initializeSaveFile(context)
    }

    context.getLastDeviceLocation { result ->
        result?.let { latLng ->
            location.value =
                LatLng(
                    latLng.latitude,
                    latLng.longitude,
                )
        } ?: with(context) {
            shortToast(text = getString(R.string.global_no_location))
        }
    }

    SetSystemStatusBar(
        !(design.mIsStatusBarWhite ?: masterDesignArgs.mIsStatusBarWhite),
        Color.Transparent,
    )

    ScreenWrapper(
        withTopBar = false,
        overrideStatusBar = true,
        screenWrapperState = dashboardViewModel.wrapperState,
        masterDesignArgs = masterDesignArgs,
        moduleDesignArgs = design,
    ) {
        RootContainer(
            masterDesignArgs = masterDesignArgs,
            moduleDesignArgs = design,
        ) {
            item {
                HeaderImageCard(
                    image = dashboardViewModel.headerImage,
                    spaceToTop = dashboardViewModel.dashboardDesignArgs.spaceToTop,
                    overrideHeaderImageHeight = design.headerImageHeight,
                    moduleDesignArgs = design,
                    masterDesignArgs = masterDesignArgs,
                ) {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize(),
                    ) {
                        Text(
                            text = getDateString(),
                            style = masterDesignArgs.normalTextStyle,
                            color = Color.White,
                            textAlign = TextAlign.Start,
                        )

                        Spacer(
                            modifier =
                                Modifier
                                    .height(16.dp),
                        )

                        Text(
                            text = stringResource(id = R.string.dashboard_header_text),
                            style = masterDesignArgs.bigTextStyle,
                            color = Color.White,
                            textAlign = TextAlign.Start,
                        )
                    }
                }
            }

            items(dashboardWidgetList) { widgetModule ->
                when (widgetModule) {
                    WidgetModules.JOB -> {
                        JobWidget(
                            navController = navController,
                        )
                    }

                    WidgetModules.EVENT -> {
                        DistrictEventWidget(
                            navController = navController,
                        )
                    }

                    WidgetModules.WASTE_A -> {
                        WasteWidget(
                            navController = navController,
                            isWidgetVisible = dashboardViewModel.isShowWasteDashboardEnabled,
                            previewCount = 2,
                            installationId = Login.getInstallation().installationId,
                        )
                    }

                    WidgetModules.WASTE_B -> {
                        WasteWidgetCalendar(
                            navController = navController,
                            isWidgetVisible = dashboardViewModel.isShowWasteDashboardEnabled,
                            installationId = Login.getInstallation().installationId,
                        )
                    }

                    WidgetModules.WEATHER -> {
                        WeatherWidget(
                            navController = navController,
                            cityName = R.string.city_name,
                            isMocked = IS_MOCKED,
                            initialLocation = location.value,
                        )
                    }

                    WidgetModules.MAP -> {
                        MapWidget(
                            navController = navController,
                            initialLocation = location.value,
                        )
                    }

                    WidgetModules.TOWNHALL -> {
                        TownhallWidget(
                            navController = navController,
                        )
                    }

                    WidgetModules.SERVICE -> {
                        ServiceWidget(
                            navController = navController,
                        )
                    }

                    WidgetModules.PRESS_RELEASE -> {
                        PressReleaseWidget(
                            navController = navController,
                            isMocked = OSCAProperties.isMocked,
                            placeholderImageId = R.drawable.mensch_solingen_round,
                        )
                    }

                    WidgetModules.DISTRICT -> {
                        val districtViewModel: DistrictViewModel = sharedHiltViewModel()

                        DistrictWidget(
                            navController = navController,
                            cityName = R.string.city_name,
                            districtViewModel = districtViewModel,
                        )
                    }

                    WidgetModules.ENVIRONMENT -> {
                        val indexOfSelf = dashboardWidgetList.indexOf(WidgetModules.ENVIRONMENT)
                        val upIndex = if (indexOfSelf - 1 < 0) 0 else indexOfSelf - 1
                        val downIndex = indexOfSelf + 1
                        WidgetContainer(
                            allowEditing = USE_WIDGETS,
                            canUp = indexOfSelf - 1 >= 0,
                            canDown = indexOfSelf < dashboardWidgetList.lastIndex,
                            moveUp = {
                                /*
                                dashboardWidgetList.removeAt(indexOfSelf)
                                dashboardWidgetList.add(upIndex, WidgetModules.ENVIRONMENT)
                                it.value = false
                                // isEditing.value = false
                                dashboardViewModel.saveDashboard(
                                    context,
                                    dashboardWidgetList,
                                )#
                                 */
                            },
                            moveDown = {
                                /*
                                dashboardWidgetList.removeAt(indexOfSelf)
                                dashboardWidgetList.add(downIndex, WidgetModules.ENVIRONMENT)
                                it.value = false
                                // isEditing.value = false
                                dashboardViewModel.saveDashboard(
                                    context,
                                    dashboardWidgetList,
                                )
                                 */
                            },
                            masterDesignArgs = masterDesignArgs,
                            moduleDesignArgs = design,
                        ) {
                            EnvironmentWidget(
                                navController = navController,
                                cityName = R.string.city_name,
                                isMocked = IS_MOCKED,
                            )
                        }
                    }
                }

                sharedViewModel.resetBottomNavBar()
            }
        }
    }
}

fun getDateString(): String {
    val now = LocalDate.now()
    val weekDay = now.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.GERMAN)
    val day = now.dayOfMonth
    val monthName = now.month.getDisplayName(TextStyle.FULL, Locale.GERMAN)
    val year = now.year

    return "$weekDay, $day. $monthName $year"
}
