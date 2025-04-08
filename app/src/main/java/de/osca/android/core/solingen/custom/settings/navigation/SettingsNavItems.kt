package de.osca.android.core.solingen.custom.settings.navigation

import androidx.navigation.navDeepLink
import de.osca.android.core.solingen.R
import de.osca.android.essentials.domain.entity.navigation.NavigationItem

sealed class SettingsNavItems {
    object SettingsNavItem : NavigationItem(
        title = R.string.navbar_settings,
        route = "settings",
        icon = R.drawable.ic_main_settings,
        deepLinks = listOf(navDeepLink { uriPattern = "solingen://settings" }),
    )

    object SettingsImprintNavItem : NavigationItem(
        title = R.string.imprint_title,
        route = "imprint",
        icon = R.drawable.ic_main_settings,
    )

    object SettingsDataPrivacyNavItem : NavigationItem(
        title = R.string.dataprivacy_title,
        route = "dataprivacy",
        icon = R.drawable.ic_main_settings,
    )
}
