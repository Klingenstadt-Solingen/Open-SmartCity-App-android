package de.osca.android.core.solingen.custom.appointments.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import de.osca.android.core.solingen.custom.appointments.presentation.components.AppointmentElement
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.design.RootContainer
import de.osca.android.essentials.presentation.component.design.SimpleSpacedList
import de.osca.android.essentials.presentation.component.screen_wrapper.ScreenWrapper
import de.osca.android.essentials.presentation.component.topbar.ScreenTopBar
import de.osca.android.essentials.presentation.nav_items.EssentialsNavItems

@Composable
fun AppointmentScreen(
    navController: NavController,
    ref: String? = null,
    title: String? = null,
    appointmentViewModel: AppointmentViewModel = hiltViewModel(),
    masterDesignArgs: MasterDesignArgs = appointmentViewModel.defaultDesignArgs,
) {
    val design = appointmentViewModel.appointmentDesignArgs

    LaunchedEffect(Unit) {
        appointmentViewModel.initialize(ref)
    }

    ScreenWrapper(
        topBar = {
            ScreenTopBar(
                title = title ?: "Liste",
                navController = navController,
                overrideBackgroundColor = design.mTopBarBackColor,
                overrideTextColor = design.mTopBarTextColor,
                masterDesignArgs = masterDesignArgs
            )
        },
        screenWrapperState = appointmentViewModel.wrapperState,
        masterDesignArgs = masterDesignArgs,
        moduleDesignArgs = design
    ) {
        RootContainer(
            masterDesignArgs = masterDesignArgs,
            moduleDesignArgs = design
        ) {
            item {
                SimpleSpacedList(
                    masterDesignArgs = masterDesignArgs
                ) {
                    appointmentViewModel.appointments.forEach { appointment ->
                        AppointmentElement(
                            masterDesignArgs = masterDesignArgs,
                            moduleDesignArgs = design,
                            titleText = appointment.title,
                            descriptionText = appointment.subtitle,
                            onClick = {
                                navController.navigate(
                                    EssentialsNavItems.getWebViewRoute(
                                        appointment.link,
                                        appointment.title,
                                        false
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}