package de.osca.android.core.solingen.custom.settings.presentation

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import de.osca.android.core.solingen.BuildConfig
import de.osca.android.core.solingen.FORCE_LOCALE
import de.osca.android.core.solingen.Login
import de.osca.android.core.solingen.R
import de.osca.android.core.solingen.USE_WIDGETS
import de.osca.android.core.solingen.custom.settings.navigation.SettingsNavItems
import de.osca.android.essentials.domain.entity.BooleanLiteralType
import de.osca.android.essentials.domain.entity.FileSystem
import de.osca.android.essentials.domain.entity.push_notification.NotificationChannel
import de.osca.android.essentials.presentation.component.design.BaseCardContainer
import de.osca.android.essentials.presentation.component.design.BaseOpenPageElement
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.design.NotificationElement
import de.osca.android.essentials.presentation.component.design.RootContainer
import de.osca.android.essentials.presentation.component.design.SimpleSpacedList
import de.osca.android.essentials.presentation.component.screen_wrapper.ScreenWrapper
import de.osca.android.essentials.presentation.component.topbar.ScreenTopBar
import de.osca.android.essentials.utils.booleanExtension.toLiteral
import de.osca.android.essentials.utils.extensions.SetSystemStatusBar
import de.osca.android.essentials.utils.extensions.shortToast
import de.osca.android.waste.navigation.WasteNavItems
import de.osca.android.weather.navigation.WeatherNavItems
import java.util.Locale

/**
 *
 */
@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    masterDesignArgs: MasterDesignArgs = settingsViewModel.defaultDesignArgs,
) {
    val context = LocalContext.current
    val design = settingsViewModel.settingsDesignArgs

    LaunchedEffect(Unit) {
        settingsViewModel.initializeAll()
    }

    settingsViewModel.saveFileObject = FileSystem.readSaveFileObject(context)

    val installation = remember { settingsViewModel.installation }
    val enableThreeClicks = remember { mutableStateOf(0) }
    val isWasteNotificationEnabled =
        remember {
            settingsViewModel.isWasteNotificationEnabled
        }

    SetSystemStatusBar(
        !(design.mIsStatusBarWhite ?: masterDesignArgs.mIsStatusBarWhite),
        Color.Transparent,
    )

    ScreenWrapper(
        topBar = {
            ScreenTopBar(
                title = stringResource(id = design.vModuleTitle),
                navController = navController,
                overrideTextColor = design.mTopBarTextColor,
                overrideBackgroundColor = design.mTopBarBackColor,
                masterDesignArgs = masterDesignArgs,
            )
        },
        screenWrapperState = settingsViewModel.wrapperState,
        masterDesignArgs = masterDesignArgs,
        moduleDesignArgs = design,
    ) {
        RootContainer(
            masterDesignArgs = masterDesignArgs,
            moduleDesignArgs = design,
        ) {
            item {
                SimpleSpacedList(
                    masterDesignArgs = masterDesignArgs,
                    overrideSpace = design.mRootCardSpacing,
                ) {
                    BaseCardContainer(
                        text = stringResource(id = R.string.settings_title_weather),
                        masterDesignArgs = masterDesignArgs,
                        moduleDesignArgs = design,
                    ) {
                        SimpleSpacedList(
                            masterDesignArgs = masterDesignArgs,
                            overrideSpace = design.mRootCardSpacing,
                        ) {
                            BaseOpenPageElement(
                                text =
                                    stringResource(
                                        id = R.string.settings_weather_mystations,
                                    ) + (if (settingsViewModel.myWeatherStation.value != null) " - " + settingsViewModel.myWeatherStation.value?.name else ""),
                                onClick = {
                                    navController.navigate(WeatherNavItems.WeatherSelectionNavItem.route)
                                },
                                masterDesignArgs = masterDesignArgs,
                                moduleDesignArgs = design,
                            )
                        }
                    }
                    BaseCardContainer(
                        text = stringResource(id = R.string.waste_title),
                        masterDesignArgs = masterDesignArgs,
                        moduleDesignArgs = design,
                    ) {
                        SimpleSpacedList(
                            masterDesignArgs = masterDesignArgs,
                            overrideSpace = design.mRootCardSpacing,
                        ) {
                            BaseOpenPageElement(
                                text = stringResource(id = R.string.settings_waste_myaddress),
                                onClick = {
                                    navController.navigate(WasteNavItems.WasteAddressesNavItem.route)
                                },
                                masterDesignArgs = masterDesignArgs,
                                moduleDesignArgs = design,
                            )
                            NotificationElement(
                                initialState = settingsViewModel.isShowWasteDashboardEnabled.value,
                                notificationText = R.string.settings_show_waste_dashboard,
                                onSwitch = { isActive ->
                                    settingsViewModel.onSwitchShowWasteDashboard(isActive)
                                },
                                masterDesignArgs = masterDesignArgs,
                                moduleDesignArgs = design,
                                available = settingsViewModel.isWasteAddressSaved.value,
                                notAvailableText = "(Verfügbar bei aktiver Abfall Adresse)",
                            )
                        }
                    }
                    BaseCardContainer(
                        text = stringResource(id = R.string.settings_title_notifications),
                        moduleDesignArgs = design,
                        masterDesignArgs = masterDesignArgs,
                    ) {
                        SimpleSpacedList(
                            masterDesignArgs = masterDesignArgs,
                            overrideSpace = 0.dp,
                        ) {
                            NotificationElement(
                                initialState =
                                    Login.getSubscribedTo(
                                        NotificationChannel.ConstructionSites,
                                    ),
                                notificationText = R.string.settings_notification_construction,
                                onSwitch = { isActive ->
                                    settingsViewModel.changeNotificationSubscription(
                                        NotificationChannel.ConstructionSites,
                                        isActive,
                                    )
                                },
                                masterDesignArgs = masterDesignArgs,
                                moduleDesignArgs = design,
                            )

                            NotificationElement(
                                initialState =
                                    Login.getSubscribedTo(
                                        NotificationChannel.PressReleases,
                                    ),
                                notificationText = R.string.settings_notification_pressnews,
                                onSwitch = { isActive ->
                                    settingsViewModel.changeNotificationSubscription(
                                        NotificationChannel.PressReleases,
                                        isActive,
                                    )
                                },
                                masterDesignArgs = masterDesignArgs,
                                moduleDesignArgs = design,
                            )

                            NotificationElement(
                                initialState = isWasteNotificationEnabled.value,
                                notificationText = R.string.settings_notification_waste,
                                onSwitch = { isActive ->
                                    settingsViewModel.onSwitchWasteNotification(context, isActive)
                                    settingsViewModel.changeNotificationSubscription(
                                        NotificationChannel.Waste,
                                        isActive,
                                    )
                                },
                                masterDesignArgs = masterDesignArgs,
                                moduleDesignArgs = design,
                                available = settingsViewModel.isWasteAddressSaved.value,
                                notAvailableText = "(Verfügbar bei aktiver Abfall Adresse)",
                            )
                        }
                    }

                    BaseCardContainer(
                        text = stringResource(id = R.string.settings_title_legal),
                        moduleDesignArgs = design,
                        masterDesignArgs = masterDesignArgs,
                    ) {
                        SimpleSpacedList(
                            masterDesignArgs = masterDesignArgs,
                            overrideSpace = design.mRootCardSpacing,
                        ) {
                            BaseOpenPageElement(
                                text = stringResource(id = R.string.settings_legal_dataprivacy),
                                onClick = {
                                    navController.navigate(SettingsNavItems.SettingsDataPrivacyNavItem.route)
                                },
                                masterDesignArgs = masterDesignArgs,
                                moduleDesignArgs = design,
                            )

                            BaseOpenPageElement(
                                text = stringResource(id = R.string.settings_legal_imprint),
                                onClick = {
                                    navController.navigate(SettingsNavItems.SettingsImprintNavItem.route)
                                },
                                masterDesignArgs = masterDesignArgs,
                                moduleDesignArgs = design,
                            )
                        }
                    }

                    SimpleSpacedList(
                        masterDesignArgs = masterDesignArgs,
                        overrideSpace = (design.mRootCardSpacing ?: masterDesignArgs.spaceList) / 2,
                        alignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(
                            modifier =
                                Modifier
                                    .height(24.dp),
                        )
                        if (BuildConfig.DEBUG) {
                            Text(
                                text = settingsViewModel.settingsDesignArgs.applicationId,
                                color = design.mHintTextColor ?: masterDesignArgs.mHintTextColor,
                                style = masterDesignArgs.normalTextStyle,
                            )

                            Text(
                                text =
                                    installation.value?.installationId
                                        ?: "no installation found",
                                color = design.mHintTextColor ?: masterDesignArgs.mHintTextColor,
                                style = masterDesignArgs.normalTextStyle,
                            )
                        }

                        SimpleSpacedList(
                            masterDesignArgs = masterDesignArgs,
                            overrideSpace =
                                (
                                    design.mRootCardSpacing
                                        ?: masterDesignArgs.spaceList
                                ) / 8,
                            alignment = Alignment.CenterHorizontally,
                        ) {
                            if (BuildConfig.DEBUG) {
                                Text(
                                    text = "Build Type [${settingsViewModel.settingsDesignArgs.buildVType}]",
                                    color =
                                        design.mHintTextColor
                                            ?: masterDesignArgs.mHintTextColor,
                                    style = masterDesignArgs.subtitleTextStyle,
                                )
                            }
                            Text(
                                text = "SDK Version min ${settingsViewModel.settingsDesignArgs.buildSdkMin} target ${settingsViewModel.settingsDesignArgs.buildSdkTarget} compile ${settingsViewModel.settingsDesignArgs.buildSdkCompile}",
                                color = design.mHintTextColor ?: masterDesignArgs.mHintTextColor,
                                style = masterDesignArgs.subtitleTextStyle,
                            )
                        }
                        if (BuildConfig.DEBUG) {
                            Text(
                                text = "Build Version ${settingsViewModel.settingsDesignArgs.buildVersion}",
                                color = design.mHintTextColor ?: masterDesignArgs.mHintTextColor,
                                style = masterDesignArgs.bodyTextStyle,
                                modifier =
                                    Modifier
                                        .clickable {
                                            if (!USE_WIDGETS) {
                                                enableThreeClicks.value += 1

                                                if (enableThreeClicks.value >= 3) {
                                                    USE_WIDGETS = true

                                                    val literal =
                                                        USE_WIDGETS.toLiteral(
                                                            context,
                                                            BooleanLiteralType.ON_OFF,
                                                        ).lowercase()
                                                    val message =
                                                        context.getString(R.string.widget_status_message) + literal
                                                    context.shortToast(message)

                                                    enableThreeClicks.value = 0
                                                }
                                            } else {
                                                USE_WIDGETS = false

                                                val literal =
                                                    USE_WIDGETS.toLiteral(
                                                        context,
                                                        BooleanLiteralType.ON_OFF,
                                                    ).lowercase()
                                                val message =
                                                    context.getString(R.string.widget_status_message) + literal
                                                context.shortToast(message)
                                            }
                                        },
                            )

                            Text(
                                text = "Locale ${Locale.getDefault().displayLanguage}",
                                color = design.mHintTextColor ?: masterDesignArgs.mHintTextColor,
                                style = masterDesignArgs.bodyTextStyle,
                                modifier =
                                    Modifier
                                        .clickable {
                                            FORCE_LOCALE =
                                                if (FORCE_LOCALE == Locale.ENGLISH) {
                                                    Locale.GERMAN
                                                } else if (FORCE_LOCALE == Locale.GERMAN) {
                                                    Locale.ENGLISH
                                                } else {
                                                    Locale.ENGLISH
                                                }

                                            FORCE_LOCALE?.let {
                                                Locale.setDefault(it)
                                            }
                                            val config: Configuration = context.resources.configuration
                                            config.locale = FORCE_LOCALE
                                            context.resources.updateConfiguration(
                                                config,
                                                context.resources.displayMetrics,
                                            )

                                            context.shortToast(FORCE_LOCALE?.displayLanguage ?: "---")
                                        },
                            )
                        }
                        Spacer(
                            modifier =
                                Modifier
                                    .height(24.dp),
                        )
                    }
                }
            }
        }
    }
}
