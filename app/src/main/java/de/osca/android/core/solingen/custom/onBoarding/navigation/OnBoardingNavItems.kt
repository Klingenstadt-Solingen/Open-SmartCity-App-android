package de.osca.android.core.solingen.custom.onBoarding.navigation

import de.osca.android.core.solingen.R
import de.osca.android.essentials.domain.entity.navigation.NavigationItem

sealed class OnBoardingNavItems {
    object OnBoardingNavItem : NavigationItem(
        title = R.string.onBoarding_title,
        route = "onBoarding",
        icon = R.drawable.ic_circle
    )
}