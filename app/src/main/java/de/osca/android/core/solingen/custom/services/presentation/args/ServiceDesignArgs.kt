package de.osca.android.core.solingen.custom.services.presentation.args

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import de.osca.android.essentials.presentation.component.design.ModuleDesignArgs
import de.osca.android.essentials.presentation.component.design.WidgetDesignArgs

interface ServiceDesignArgs : ModuleDesignArgs, WidgetDesignArgs {
    val textAlign: TextAlign?
    val cardHeight: Dp
    val constraintWidth: Dp?
    val useImagePadding: Boolean
    val externalLinkIconColor: Color
    val externalLinkTextColor: Color
    val previewCountForWidget: Int
}