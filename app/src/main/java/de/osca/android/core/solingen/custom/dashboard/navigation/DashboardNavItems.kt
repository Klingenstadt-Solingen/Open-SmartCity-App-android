package de.osca.android.core.solingen.custom.dashboard.navigation

import androidx.navigation.navDeepLink
import de.osca.android.core.solingen.R
import de.osca.android.essentials.domain.entity.navigation.NavigationItem

sealed class DashboardNavItems {
    object DashboardNavItem : NavigationItem(
        title = R.string.navbar_dashboard,
        route = "dashboard",
        icon = R.drawable.ic_main_city,
        deepLinks = listOf(navDeepLink { uriPattern = "solingen://home" }),
    )
}
