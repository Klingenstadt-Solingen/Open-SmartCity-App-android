package de.osca.android.core.solingen.custom.dashboard.presentation.args

import androidx.compose.ui.unit.Dp
import de.osca.android.core.solingen.custom.dashboard.entity.WidgetModules
import de.osca.android.essentials.presentation.component.design.ModuleDesignArgs

interface DashboardDesignArgs : ModuleDesignArgs {
    val showSystemToolbar: Boolean
    val switchingHeaderImages: List<Int>
    val widgetsToShow: List<WidgetModules>
    val spaceToTop: Dp
    val headerImageHeight: Dp
    val spaceBetweenWidgets: Dp
}