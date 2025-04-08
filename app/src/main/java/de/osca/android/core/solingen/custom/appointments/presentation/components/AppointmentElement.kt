package de.osca.android.core.solingen.custom.appointments.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import de.osca.android.essentials.presentation.component.design.BaseCardContainer
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.design.ModuleDesignArgs

@Composable
fun AppointmentElement(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    titleText: String? = "",
    descriptionText: String? = "",
    onClick: (() -> Unit)? = null
) {
    BaseCardContainer(
        onClick = onClick,
        useContentPadding = false,
        moduleDesignArgs = moduleDesignArgs,
        masterDesignArgs = masterDesignArgs
    ){
        Column(modifier = Modifier
            .padding(moduleDesignArgs.mCardContentPadding ?: masterDesignArgs.mCardContentPadding)
        ) {
            Text(
                text = titleText ?: "",
                style = masterDesignArgs.overlineTextStyle,
                color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = descriptionText ?: "",
                style = masterDesignArgs.normalTextStyle,
                color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}