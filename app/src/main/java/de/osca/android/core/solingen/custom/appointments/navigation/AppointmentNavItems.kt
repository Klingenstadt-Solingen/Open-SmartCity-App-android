package de.osca.android.core.solingen.custom.appointments.navigation

import androidx.navigation.navDeepLink
import de.osca.android.core.solingen.R
import de.osca.android.essentials.domain.entity.navigation.NavigationItem

sealed class AppointmentNavItems {
    object AppointmentNavItem : NavigationItem(
        title = R.string.navbar_services,
        route = "appointments",
        icon = R.drawable.ic_circle,
        deepLinks = listOf(navDeepLink { uriPattern = "solingen://appointments" }),
    )
}
