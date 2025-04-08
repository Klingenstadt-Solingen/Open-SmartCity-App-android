package de.osca.android.core.solingen.custom.onBoarding.presentation.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.osca.android.core.solingen.R
import de.osca.android.core.solingen.custom.onBoarding.presentation.OnBoardingViewModel
import de.osca.android.essentials.presentation.component.design.MainButton
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.design.SimpleSpacedList

@Composable
fun PageOneScreen(
    onBoardingViewModel: OnBoardingViewModel,
    masterDesignArgs: MasterDesignArgs = onBoardingViewModel.defaultDesignArgs,
    onClick: () -> Unit = { }
) {
    val design = onBoardingViewModel.onBoardingDesignArgs

    Column(
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = design.cityLogo),
            contentDescription = "headerImage",
            modifier = Modifier
                .size(180.dp)
        )

        Column {
            if (design.cityPrefix != null) {
                Text(
                    text = design.cityPrefix?.let { stringResource(id = it) } ?: "",
                    style = masterDesignArgs.captionTextStyle,
                    color = masterDesignArgs.appSpecificColor
                )
            }

            Text(
                text = stringResource(id = design.cityName),
                style = masterDesignArgs.reallyBigTextStyle,
                color = masterDesignArgs.highlightColor
            )
        }

        SimpleSpacedList(
            masterDesignArgs = masterDesignArgs
        ) {
            for (item in design.contentPage1) {
                Text(
                    text = stringResource(id = item),
                    style = masterDesignArgs.normalTextStyle,
                    color = design.mScreenTextColor ?: masterDesignArgs.mScreenTextColor
                )
            }
        }

        Spacer(
            modifier = Modifier
                .weight(1f)
        )

        MainButton(
            buttonText = stringResource(id = R.string.onBoarding_next_button),
            onClick = {
                onClick()
            },
            masterDesignArgs = masterDesignArgs,
            moduleDesignArgs = design
        )
    }
}