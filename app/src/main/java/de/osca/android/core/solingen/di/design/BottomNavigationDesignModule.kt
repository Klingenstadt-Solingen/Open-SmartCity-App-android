package de.osca.android.core.solingen.di.design

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.osca.android.core.solingen.R
import de.osca.android.core.solingen.custom.dashboard.navigation.DashboardNavItems
import de.osca.android.core.solingen.custom.services.navigation.ServicesNavItems
import de.osca.android.core.solingen.custom.settings.navigation.SettingsNavItems
import de.osca.android.core.solingen.custom.townhall.navigation.TownhallNavItems
import de.osca.android.core.solingen.ui.theme.CLR_BackgroundSheet
import de.osca.android.core.solingen.ui.theme.CLR_City
import de.osca.android.core.solingen.ui.theme.CLR_TextSheet
import de.osca.android.essentials.domain.entity.navigation.NavigationItem
import de.osca.android.essentials.presentation.component.bottom_navigation.BottomNavigationDesignArgs
import de.osca.android.press_release.navigation.PressReleaseNavItems
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BottomNavigationDesignModule {

    @Singleton
    @Provides
    fun providesBottomNavigationDesignArgs(): BottomNavigationDesignArgs {
        return object : BottomNavigationDesignArgs {
            override val backgroundColor: Color = CLR_BackgroundSheet()
            override val contentColor: Color = CLR_TextSheet()
            override val elevation: Dp = 8.dp
            override val selectedColor: Color = CLR_City()
            override val items: MutableMap<NavigationItem, Pair<Int?, String?>> = mutableMapOf(
                DashboardNavItems.DashboardNavItem to Pair(null, null),
                TownhallNavItems.TownhallNavItem to Pair(null, null),
                PressReleaseNavItems.PressReleaseNavItem to Pair(R.drawable.meldungen_svg, null),
                ServicesNavItems.ServicesNavItem to Pair(null, null),
                SettingsNavItems.SettingsNavItem to Pair(null, null)
            )
            override val showLabel: Boolean = true
        }
    }
}