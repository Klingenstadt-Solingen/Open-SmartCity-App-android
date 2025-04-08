package de.osca.android.core.solingen.custom.services.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import de.osca.android.core.solingen.R
import de.osca.android.essentials.presentation.component.design.BaseCardContainer
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.design.ModuleDesignArgs

@Composable
fun ServiceElement(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    titleText: String? = "",
    descriptionText: String? = "",
    imageUrl: String? = null,
    isExternalLink: Boolean = false,
    externalLinkIconColorOverride: Color? = null,
    externalLinkTextColorOverride: Color? = null,
    onClick: (() -> Unit)? = null
) {
    BaseCardContainer(
        onClick = onClick,
        useContentPadding = false,
        moduleDesignArgs = moduleDesignArgs,
        masterDesignArgs = masterDesignArgs
    ){
        Row {
            Card(
                modifier = Modifier
                    .size(100.dp),
                shape = RoundedCornerShape(0.dp),
                backgroundColor = moduleDesignArgs.mCardBackColor
                    ?: masterDesignArgs.mCardBackColor,
                elevation = 0.dp
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(imageUrl).apply(block = {
                            error(R.drawable.mensch_solingen_round)
                        }).build()
                    ),
                    contentDescription = "pressReleaseImage",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .padding(
                        moduleDesignArgs.mCardContentPadding ?: masterDesignArgs.mCardContentPadding
                    )
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = titleText ?: "",
                        style = masterDesignArgs.overlineTextStyle,
                        color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    if(isExternalLink) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_external_link),
                            contentDescription = "isExternalLink",
                            tint = externalLinkIconColorOverride ?: masterDesignArgs.mDialogsBackColor,
                            modifier = Modifier
                                .size(25.dp)
                        )
                    }
                }
                Text(
                    text = descriptionText ?: "",
                    style = masterDesignArgs.normalTextStyle,
                    color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}