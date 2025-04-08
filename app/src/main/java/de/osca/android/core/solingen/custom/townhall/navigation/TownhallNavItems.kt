package de.osca.android.core.solingen.custom.townhall.navigation

import androidx.navigation.navDeepLink
import de.osca.android.core.solingen.R
import de.osca.android.essentials.domain.entity.navigation.NavigationItem

sealed class TownhallNavItems {
    object TownhallNavItem : NavigationItem(
        title = R.string.navbar_townhall,
        route = "townhall",
        icon = R.drawable.ic_main_townhall,
        deepLinks = listOf(navDeepLink { uriPattern = "solingen://townhall" }),
    )
}
