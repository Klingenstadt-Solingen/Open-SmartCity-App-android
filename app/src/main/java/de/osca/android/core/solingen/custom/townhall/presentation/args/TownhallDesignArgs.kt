package de.osca.android.core.solingen.custom.townhall.presentation.args

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import de.osca.android.essentials.presentation.component.design.ModuleDesignArgs
import de.osca.android.essentials.presentation.component.design.WidgetDesignArgs

interface TownhallDesignArgs : ModuleDesignArgs, WidgetDesignArgs {
    val textAlign: TextAlign?
    val iconColor: Color?
    val showArrowIcon: Boolean
    val showTownhallIcon: Boolean
    val columnCount: Int
}