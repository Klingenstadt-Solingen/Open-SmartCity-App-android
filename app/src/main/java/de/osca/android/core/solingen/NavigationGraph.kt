package de.osca.android.core.solingen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.android.gms.maps.model.LatLng
import de.osca.android.contact.navigation.ContactNavItems
import de.osca.android.contact.presentation.ContactScreen
import de.osca.android.core.solingen.custom.appointments.navigation.AppointmentNavItems
import de.osca.android.core.solingen.custom.appointments.presentation.AppointmentScreen
import de.osca.android.core.solingen.custom.dashboard.navigation.DashboardNavItems
import de.osca.android.core.solingen.custom.dashboard.presentation.DashboardScreen
import de.osca.android.core.solingen.custom.onBoarding.navigation.OnBoardingNavItems
import de.osca.android.core.solingen.custom.onBoarding.presentation.OnBoardingScreen
import de.osca.android.core.solingen.custom.services.navigation.ServicesNavItems
import de.osca.android.core.solingen.custom.services.presentation.ServicesScreen
import de.osca.android.core.solingen.custom.settings.navigation.SettingsNavItems
import de.osca.android.core.solingen.custom.settings.presentation.SettingsScreen
import de.osca.android.core.solingen.custom.settings.presentation.SettingsViewModel
import de.osca.android.core.solingen.custom.settings.presentation.dataPrivacy.DataPrivacyScreen
import de.osca.android.core.solingen.custom.settings.presentation.imprint.ImprintScreen
import de.osca.android.core.solingen.custom.splash.SplashViewModel
import de.osca.android.core.solingen.custom.townhall.navigation.TownhallNavItems
import de.osca.android.core.solingen.custom.townhall.presentation.TownhallScreen
import de.osca.android.coworking.navigation.CoworkingNavItems
import de.osca.android.coworking.presentation.CoworkingScreen
import de.osca.android.defect.navigation.DefectNavItems
import de.osca.android.defect.presentation.DefectScreen
import de.osca.android.district.core.navigation.LocalNavigationController
import de.osca.android.district.core.navigation.districtNestedGraph
import de.osca.android.district.core.viewmodel.NavigationViewModel
import de.osca.android.environment.navigation.EnvironmentNavItems
import de.osca.android.environment.presentation.environment.EnvironmentScreen
import de.osca.android.environment.presentation.environment.viewmodel.EnvironmentViewModel
import de.osca.android.essentials.domain.entity.push_notification.NotificationChannel
import de.osca.android.essentials.presentation.MainActivitySharedViewModel
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.web_view.WebViewScreen
import de.osca.android.essentials.presentation.nav_items.EssentialsNavItems
import de.osca.android.essentials.utils.extensions.composableForNav
import de.osca.android.essentials.utils.extensions.getFromPreviousState
import de.osca.android.events.navigation.EventsNavItems
import de.osca.android.events.presentation.event_details.EventDetailsScreen
import de.osca.android.events.presentation.events.EventsScreen
import de.osca.android.jobs.navigation.JobsNavItems
import de.osca.android.jobs.presentation.JobsScreen
import de.osca.android.map.navigation.MapNavItems
import de.osca.android.map.presentation.map.MapScreen
import de.osca.android.mobility.navigation.MobilityNavItems
import de.osca.android.mobility.presentation.dashboard.MobilityDashboardScreen
import de.osca.android.press_release.navigation.PressReleaseNavItems
import de.osca.android.press_release.presentation.press_release.PressReleaseScreen
import de.osca.android.press_release.presentation.read_press_release.ReadPressReleaseScreen
import de.osca.android.public_transport.domain.entity.RouteResponse
import de.osca.android.public_transport.navigation.PublicTransportNavItems
import de.osca.android.public_transport.presentation.public_transport.PublicTransportScreen
import de.osca.android.public_transport.presentation.route_details.TransportDetailsScreen
import de.osca.android.sgart.navigation.ArtNavItems
import de.osca.android.sgart.navigation.ArtNavItems.Companion.ART_ID_ARGS
import de.osca.android.sgart.presentation.ArtDetailScreen
import de.osca.android.sgart.presentation.ArtScreen
import de.osca.android.waste.domain.entity.WasteLocationType
import de.osca.android.waste.navigation.WasteNavItems
import de.osca.android.waste.presentation.WasteScreen
import de.osca.android.waste.presentation.appointments.WasteNextAppointmentList
import de.osca.android.waste.presentation.moreInfo.WasteDetailedInfoScreen
import de.osca.android.waste.presentation.moreInfo.WasteGreenAndClothesScreen
import de.osca.android.waste.presentation.moreInfo.WasteMoreInfoScreen
import de.osca.android.waste.presentation.moreInfo.WasteOverviewScreen
import de.osca.android.waste.presentation.moreInfo.WasteSpecialInfoScreen
import de.osca.android.waste.widget.list.WasteAddressScreen
import de.osca.android.weather.navigation.WeatherNavItems
import de.osca.android.weather.presentation.weather.WeatherScreen
import de.osca.android.weather.presentation.weather.lists.WeatherListScreen
import de.osca.android.weather.presentation.weather.lists.WeatherStationSelectionScreen

val LocalNavigationController =
    compositionLocalOf<NavController> { error("No Navigation Controller found!") }

@OptIn(
    ExperimentalMaterialApi::class,
)
@Composable
fun NavigationGraph(
    masterDesignArgs: MasterDesignArgs,
    navController: NavHostController,
    sharedViewModel: MainActivitySharedViewModel,
    splashViewModel: SplashViewModel = hiltViewModel(),
    environmentViewModel: EnvironmentViewModel = hiltViewModel(),
    districtNavigationViewModel: NavigationViewModel = hiltViewModel(),
) {
    CompositionLocalProvider(LocalNavigationController provides navController) {
        NavHost(
            navController = navController,
            startDestination =
                if (splashViewModel.isFirstStart) {
                    OnBoardingNavItems.OnBoardingNavItem.route
                } else {
                    DashboardNavItems.DashboardNavItem.route
                },
        ) {
            /**
             * ON BOARDING
             */
            composableForNav(OnBoardingNavItems.OnBoardingNavItem) {
                OnBoardingScreen(
                    navController = navController,
                )
            }

            /**
             * DASHBOARD
             */
            composableForNav(DashboardNavItems.DashboardNavItem) {
                val activity = (LocalContext.current as? Activity)
                BackHandler(enabled = true) {
                    activity?.finish()
                }

                sharedViewModel.setSelectedTabbarNavItem(navigationItem = DashboardNavItems.DashboardNavItem)

                DashboardScreen(
                    sharedViewModel = sharedViewModel,
                    navController = navController,
                    initialLocation = OSCAProperties.defaultLatLng,
                )
            }

            /**
             * ESSENTIALS
             */
            composableForNav(EssentialsNavItems.DataPrivacyNavItem) {
                val viewModel: SettingsViewModel = hiltViewModel()
                sharedViewModel.resetBottomNavBar()

                DataPrivacyScreen(
                    settingsViewModel = viewModel,
                    navController = navController,
                    masterDesignArgs = masterDesignArgs,
                )
            }
            composableForNav(EssentialsNavItems.WebViewNavItem) { backStackEntry ->
                val url =
                    backStackEntry.arguments?.getString(EssentialsNavItems.ARG_WEB_VIEW_URL, "")
                val title =
                    backStackEntry.arguments?.getString(EssentialsNavItems.ARG_WEB_VIEW_TITLE, "")
                val share =
                    backStackEntry.arguments?.getBoolean(
                        EssentialsNavItems.ARG_WEB_VIEW_SHARE,
                        false,
                    )
                sharedViewModel.resetBottomNavBar()
                WebViewScreen(
                    navController = navController,
                    url = url,
                    title = title,
                    hasShareIcon = share ?: false,
                )
            }

            /**
             * COWORKING
             */
            composableForNav(CoworkingNavItems.CoworkingNavItem) {
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = CoworkingNavItems.CoworkingNavItem)

                CoworkingScreen(
                    navController = navController,
                )
            }

            /**
             * CONTACT
             */
            composableForNav(ContactNavItems.ContactFormNavItem) {
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = ContactNavItems.ContactFormNavItem)

                ContactScreen(
                    navController = navController,
                )
            }

            /**
             * EVENTS
             */
            composableForNav(EventsNavItems.EventsNavItem) {
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = EventsNavItems.EventsNavItem)

                EventsScreen(
                    navController = navController,
                )
            }
            composableForNav(EventsNavItems.EventDetailsNavItem) { backStackEntry ->
                val objectId = backStackEntry.arguments?.getString(EventsNavItems.ARG_EVENT) ?: ""
                sharedViewModel.resetBottomNavBar()
                EventDetailsScreen(
                    navController = navController,
                    objectId,
                )
            }

            /**
             * JOBS
             */
            composableForNav(JobsNavItems.JobsNavItem) { backStackEntry ->
                val objectId = backStackEntry.arguments?.getString(EventsNavItems.ARG_EVENT)
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = JobsNavItems.JobsNavItem)

                JobsScreen(
                    navController = navController,
                    objectId,
                )
            }

            /**
             * PRESS RELEASE
             */
            composableForNav(PressReleaseNavItems.PressReleaseNavItem) {
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = PressReleaseNavItems.PressReleaseNavItem)

                PressReleaseScreen(
                    navController = navController,
                    isMocked = OSCAProperties.isMocked,
                    placeholderImageId = R.drawable.mensch_solingen_round,
                )
            }
            composableForNav(PressReleaseNavItems.ReadPressReleaseNavItem) { backStackEntry ->
                val pressReleaseId =
                    backStackEntry.arguments?.getString(
                        PressReleaseNavItems.ARG_PRESS_RELEASE_ID,
                        "",
                    )
                sharedViewModel.resetBottomNavBar()
                ReadPressReleaseScreen(
                    id = pressReleaseId,
                    navController = navController,
                )
            }

            /**
             * WASTE
             */
            composableForNav(WasteNavItems.WasteNavItem) {
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = WasteNavItems.WasteNavItem)

                WasteScreen(
                    navController = navController,
                    installationId = Login.getInstallation().installationId,
                    wasteNotificationCallback = {
                        Login.updateChannels(NotificationChannel.Waste, it)
                    },
                )
            }
            composableForNav(WasteNavItems.WasteOverviewNavItem) { backStackEntry ->
                val title =
                    backStackEntry.arguments?.getString(WasteNavItems.WASTE_OVERVIEW_TITLE, "")
                sharedViewModel.resetBottomNavBar()
                WasteOverviewScreen(
                    navController = navController,
                    title = title,
                )
            }
            composableForNav(WasteNavItems.WasteSpecialInfoNavItem) { backStackEntry ->
                val title =
                    backStackEntry.arguments?.getString(WasteNavItems.WASTE_SPECIAL_TITLE, "")
                sharedViewModel.resetBottomNavBar()
                WasteSpecialInfoScreen(
                    navController = navController,
                    title = title,
                )
            }
            composableForNav(WasteNavItems.WasteMoreInfoNavItem) { backStackEntry ->
                val objectId = backStackEntry.arguments?.getString(WasteNavItems.WASTE_ID, "")
                sharedViewModel.resetBottomNavBar()
                WasteMoreInfoScreen(
                    objectId = objectId,
                    navController = navController,
                )
            }
            composableForNav(WasteNavItems.WasteTypeInfoNavItem) {
                sharedViewModel.resetBottomNavBar()
                WasteDetailedInfoScreen(
                    navController = navController,
                )
            }
            composableForNav(WasteNavItems.WasteAddressesNavItem) {
                sharedViewModel.resetBottomNavBar()
                WasteAddressScreen(
                    navController = navController,
                    installationId = Login.getInstallation().installationId,
                )
            }

            composableForNav(WasteNavItems.WasteNextAppointmentListNavItem) {
                sharedViewModel.resetBottomNavBar()
                WasteNextAppointmentList(
                    navController = navController,
                    installationId = Login.getInstallation().installationId,
                )
            }

            composableForNav(WasteNavItems.WasteGreenMobileNavItem) {
                //sharedViewModel.resetBottomNavBar()
                WasteGreenAndClothesScreen(
                    navController = navController,
                    wasteLocationType = WasteLocationType.Grünschnittcontainer,
                )
            }

            composableForNav(WasteNavItems.WasteClothesNavItem) {
                //sharedViewModel.resetBottomNavBar()
                WasteGreenAndClothesScreen(
                    navController = navController,
                    wasteLocationType = WasteLocationType.Altkleidercontainer,
                )
            }

            /**
             * DEFECT
             */
            composableForNav(DefectNavItems.DefectNavItem) {
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = DefectNavItems.DefectNavItem)

                DefectScreen(
                    navController = navController,
                    initialLocation = OSCAProperties.defaultLatLng,
                    boundingBottomLeft = LatLng(51.115550, 6.983295), // 51.115550, 6.983295
                    boundingTopRight = LatLng(51.215931, 7.102951), // 51.215931, 7.102951
                    maxSearchResults = 10,
                    provider = stringResource(id = R.string.app_defect_provider),
                )
            }

            /**
             * PUBLIC TRANSPORT
             */
            composableForNav(PublicTransportNavItems.PublicTransport) { backStackEntry ->
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = PublicTransportNavItems.PublicTransport)

                val fromId =
                    backStackEntry.arguments?.getString(PublicTransportNavItems.ARG_TRANSPORT_FROM_ID)
                val toId =
                    backStackEntry.arguments?.getString(PublicTransportNavItems.ARG_TRANSPORT_TO_ID)
                val dateTimeArg =
                    backStackEntry.arguments?.getString(PublicTransportNavItems.ARG_TRANSPORT_DATETIME)
                val arrDep =
                    backStackEntry.arguments?.getString(PublicTransportNavItems.ARG_TRANSPORT_ARRDEP)

                PublicTransportScreen(
                    fromId = fromId,
                    toId = toId,
                    dateTimeArg = dateTimeArg,
                    arrDep = arrDep,
                    navController = navController,
                )
            }
            composableForNav(PublicTransportNavItems.RouteDetails) {
                sharedViewModel.resetBottomNavBar()

                val trip =
                    navController.getFromPreviousState<RouteResponse>(
                        PublicTransportNavItems.RouteDetails.BUNDLE_KEY_TRIP,
                    )

                TransportDetailsScreen(navController = navController, route = trip)
            }

            /**
             * WEATHER
             */
            composableForNav(WeatherNavItems.WeatherNavItem) { backStackEntry ->
                val objectId =
                    backStackEntry.arguments?.getString(WeatherNavItems.ARG_WEATHER, "") ?: ""
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = WeatherNavItems.WeatherNavItem)

                WeatherScreen(
                    objectId = objectId,
                    navController = navController,
                    isMocked = OSCAProperties.isMocked,
                    weatherGridElementActions =
                        listOf(
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                        ),
                )
            }

            /**
             * MAP
             */
            composableForNav(MapNavItems.MapNavItem) { backStackEntry ->
                val id = backStackEntry.arguments?.getString(MapNavItems.ARG_POI_ID)
                val type =
                    backStackEntry.arguments?.get(MapNavItems.ARG_TYPE) as MapNavItems.PoiType?
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = MapNavItems.MapNavItem)

                MapScreen(
                    navController = navController,
                    initializeCoordinates = OSCAProperties.defaultLatLng,
                    initializeCategory = if (type == MapNavItems.PoiType.Categories) id else null,
                    initializePoi = if (type == MapNavItems.PoiType.Detail) id else null,
                )
            }

            /**
             * SETTINGS
             */
            composableForNav(SettingsNavItems.SettingsNavItem) {
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = SettingsNavItems.SettingsNavItem)

                SettingsScreen(
                    navController = navController,
                )
            }
            composableForNav(SettingsNavItems.SettingsImprintNavItem) {
                sharedViewModel.resetBottomNavBar()
                ImprintScreen(
                    navController = navController,
                )
            }
            composableForNav(SettingsNavItems.SettingsDataPrivacyNavItem) {
                val viewModel: SettingsViewModel = hiltViewModel()
                sharedViewModel.resetBottomNavBar()
                DataPrivacyScreen(
                    settingsViewModel = viewModel,
                    navController = navController,
                    masterDesignArgs = masterDesignArgs,
                )
            }

            /**
             * WEATHER LIST
             */
            composableForNav(WeatherNavItems.WeatherSelectionNavItem) {
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = WeatherNavItems.WeatherSelectionNavItem)

                WeatherStationSelectionScreen(
                    navController = navController,
                    initialLocation = OSCAProperties.defaultLatLng,
                )
            }

            /**
             * SERVICES
             */
            composableForNav(ServicesNavItems.ServicesNavItem) {
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = ServicesNavItems.ServicesNavItem)

                ServicesScreen(
                    navController = navController,
                )
            }

            /**
             * TOWNHALL
             */
            composableForNav(TownhallNavItems.TownhallNavItem) {
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = TownhallNavItems.TownhallNavItem)

                TownhallScreen(
                    navController = navController,
                )
            }

            /**
             * WEATHER LIST
             */
            composableForNav(WeatherNavItems.WeatherListNavItem) {
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = WeatherNavItems.WeatherListNavItem)

                WeatherListScreen(
                    navController = navController,
                    isMocked = OSCAProperties.isMocked,
                    initialLocation = OSCAProperties.defaultLatLng,
                )
            }

            /**
             * ART
             */
            composableForNav(ArtNavItems.ArtFormNavItem) {
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = ArtNavItems.ArtFormNavItem)

                ArtScreen(
                    navController = navController,
                )
            }
            composableForNav(ArtNavItems.ArtDetailsNavItem) { backStackEntry ->
                val artId = backStackEntry.arguments?.getInt(ART_ID_ARGS) ?: -1
                sharedViewModel.resetBottomNavBar()
                ArtDetailScreen(
                    kunstInWaldId = artId,
                    navController = navController,
                )
            }

            /**
             * MOBILITY
             */
            composableForNav(MobilityNavItems.MobilityDashboardNavItem) {
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = MobilityNavItems.MobilityDashboardNavItem)

                MobilityDashboardScreen(
                    navController = navController,
                    initialLocation = OSCAProperties.defaultLatLng,
                )
            }

            /**
             * APPOINTMENT
             */
            composableForNav(AppointmentNavItems.AppointmentNavItem) {
                sharedViewModel.resetBottomNavBar()
                sharedViewModel.setSelectedTabbarNavItem(navigationItem = AppointmentNavItems.AppointmentNavItem)
                AppointmentScreen(
                    navController = navController,
                    ref = "termin",
                    title = "Termine",
                )
            }

            /**
             * ENVIRONMENT
             */
            composableForNav(EnvironmentNavItems.EnvironmentNavItem) {
                sharedViewModel.resetBottomNavBar()

                EnvironmentScreen(
                    environmentViewModel = environmentViewModel,
                    navController = navController,
                )
            }
            /**
             * District
             */
            districtNestedGraph(districtNavigationViewModel)
        }
    }
}
