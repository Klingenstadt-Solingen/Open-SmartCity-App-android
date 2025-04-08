package de.osca.android.core.solingen.custom.onBoarding.presentation.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import de.osca.android.core.solingen.R
import de.osca.android.core.solingen.custom.onBoarding.presentation.OnBoardingViewModel
import de.osca.android.essentials.presentation.component.design.HtmlTextWebview
import de.osca.android.essentials.presentation.component.design.MainButton
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs

@Composable
fun PageThreeScreen(
    onBoardingViewModel: OnBoardingViewModel,
    masterDesignArgs: MasterDesignArgs = onBoardingViewModel.defaultDesignArgs,
    onClick: () -> Unit = { },
    onClickBack: () -> Unit = { }
) {
    val design = onBoardingViewModel.onBoardingDesignArgs

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = stringResource(id = design.dataPrivacyTitle),
            style = masterDesignArgs.captionTextStyle,
            color = design.mScreenTextColor ?: masterDesignArgs.mScreenTextColor
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            item {
                HtmlTextWebview(
                    htmlString = onBoardingViewModel.getDataPrivacy()
                )
            }
        }

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