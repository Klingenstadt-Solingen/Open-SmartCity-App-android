package de.osca.android.core.solingen.custom.onBoarding.presentation.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import de.osca.android.core.solingen.R
import de.osca.android.core.solingen.custom.onBoarding.presentation.OnBoardingViewModel
import de.osca.android.essentials.presentation.component.design.MainButton
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.design.SimpleSpacedList

@Composable
fun PageTwoScreen(
    onBoardingViewModel: OnBoardingViewModel,
    masterDesignArgs: MasterDesignArgs = onBoardingViewModel.defaultDesignArgs,
    onClick: () -> Unit = { },
    onClickBack: () -> Unit = { }
) {
    val design = onBoardingViewModel.onBoardingDesignArgs

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        if(design.dataPrivacyImage != null) {
            Image(
                painter = painterResource(id = design.dataPrivacyImage!!),
                contentDescription = "dataPrivacyImage",
                modifier = Modifier
                    .size(180.dp)
            )
        }

        SimpleSpacedList(
            masterDesignArgs = masterDesignArgs
        ) {
            Text(
                text = stringResource(id = design.dataPrivacyTitle),
                style = masterDesignArgs.captionTextStyle,
                color = design.mScreenTextColor ?: masterDesignArgs.mScreenTextColor
            )

            SimpleSpacedList(
                masterDesignArgs = masterDesignArgs
            ) {
                for(item in design.contentPage2) {
                    Text(
                        text = stringResource(id = item),
                        style = masterDesignArgs.normalTextStyle,
                        color = design.mScreenTextColor ?: masterDesignArgs.mScreenTextColor
                    )
                }
            }
        }

        Spacer(modifier = Modifier
            .weight(1f)
        )

        Column {
            MainButton(
                buttonText = stringResource(id = R.string.onBoarding_next_button),
                onClick = {
                    onClick()
                },
                masterDesignArgs = masterDesignArgs,
                moduleDesignArgs = design
            )

            Text(
                text = stringResource(id = R.string.onBoarding_back_button),
                style = masterDesignArgs.normalTextStyle,
                color = design.mScreenTextColor ?: masterDesignArgs.mScreenTextColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .clickable {
                        onClickBack()
                    }
            )
        }
    }
}