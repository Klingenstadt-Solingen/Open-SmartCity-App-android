package de.osca.android.core.solingen.custom.onBoarding.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs

@Composable
fun PageIdentifierElement(
    masterDesignArgs: MasterDesignArgs,
    dotCount: Int,
    selectedDot: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        repeat(dotCount) {
            val color = if (it == selectedDot) masterDesignArgs.appSpecificColor else masterDesignArgs.highlightColor
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(8.dp)
            )
        }
    }
}