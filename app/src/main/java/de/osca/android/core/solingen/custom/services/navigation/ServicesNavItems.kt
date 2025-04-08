package de.osca.android.core.solingen.custom.services.navigation

import androidx.navigation.navDeepLink
import de.osca.android.core.solingen.R
import de.osca.android.essentials.domain.entity.navigation.NavigationItem

sealed class ServicesNavItems {
    object ServicesNavItem : NavigationItem(
        title = R.string.navbar_services,
        route = "services",
        icon = R.drawable.ic_main_services,
        deepLinks = listOf(navDeepLink { uriPattern = "solingen://service" }),
    )
}
