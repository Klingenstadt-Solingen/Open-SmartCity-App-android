package de.osca.android.core.solingen.custom.townhall.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import de.osca.android.core.solingen.R
import de.osca.android.essentials.presentation.component.design.BaseCardContainer
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.design.ModuleDesignArgs

@Composable
fun TownhallInfoElement(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    title: String,
    @DrawableRes iconId: Int = -1,
    iconUrl: String? = null,
    useDummies: Boolean = false,
    isOnlyOneItem: Boolean = false,
    iconTintColor: Color? = null,
    showTownhallIcon: Boolean = true,
    showArrowIcon: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        BaseCardContainer(
            masterDesignArgs = masterDesignArgs,
            moduleDesignArgs = moduleDesignArgs,
            useContentPadding = false,
            onClick = onClick
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .heightIn(100.dp)
                .padding(moduleDesignArgs.mContentPaddingForMiniCards ?: masterDesignArgs.mContentPaddingForMiniCards)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(if(isOnlyOneItem && !useDummies) .3f else 1f)
                ) {
                    Text(
                        text = title,
                        style = masterDesignArgs.bodyTextStyle,
                        color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth(),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (showTownhallIcon || showArrowIcon) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            if (showArrowIcon) {
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = "",
                                    tint = iconTintColor ?: moduleDesignArgs.mHintTextColor
                                    ?: masterDesignArgs.mHintTextColor,
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                            }

                            if (showTownhallIcon) {
                                Icon(
                                    painter = if(iconId >= 0)
                                        painterResource(id = if (iconId >= 0) iconId else R.drawable.ic_circle)
                                    else
                                        rememberAsyncImagePainter(iconUrl),
                                    contentDescription = "",
                                    tint = iconTintColor ?: moduleDesignArgs.mHintTextColor
                                    ?: masterDesignArgs.mHintTextColor,
                                    modifier = Modifier
                                        .size(45.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}