package de.osca.android.core.solingen.custom.onBoarding.presentation.pages

import android.Manifest
import android.content.Intent
import android.os.Build
import android.provider.Settings
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import de.osca.android.core.solingen.R
import de.osca.android.core.solingen.custom.onBoarding.presentation.OnBoardingViewModel
import de.osca.android.essentials.presentation.component.design.MainButton
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.design.SimpleSpacedList

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PageFourScreen(
    onBoardingViewModel: OnBoardingViewModel,
    masterDesignArgs: MasterDesignArgs = onBoardingViewModel.defaultDesignArgs,
    onClick: () -> Unit = { },
    onClickBack: () -> Unit = { }
) {
    val context = LocalContext.current
    val design = onBoardingViewModel.onBoardingDesignArgs

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        if(design.notificationImage != null) {
            Image(
                painter = painterResource(id = design.notificationImage!!),
                contentDescription = "headerImage",
                modifier = Modifier
                    .size(180.dp)
            )
        }

        SimpleSpacedList(
            masterDesignArgs = masterDesignArgs
        ) {
            Text(
                text = stringResource(id = design.notificationTitle),
                style = masterDesignArgs.captionTextStyle,
                color = masterDesignArgs.mScreenTextColor
            )

            SimpleSpacedList(
                masterDesignArgs = masterDesignArgs
            ) {
                for(item in design.contentPage3) {
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val permissionState = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
                MainButton(
                    buttonText = if (permissionState.status.isGranted) {
                        stringResource(id = R.string.onBoarding_next_notification_active)
                    } else stringResource(id = R.string.onBoarding_next_notification_grant),
                    onClick = {
                       permissionState.launchPermissionRequest()
                    },
                    masterDesignArgs = masterDesignArgs,
                    moduleDesignArgs = design,
                    enabled = !permissionState.status.isGranted
                )
                MainButton(
                    buttonText =  if (permissionState.status.isGranted) {
                        stringResource(id = R.string.onBoarding_next_button)
                    } else stringResource(id = R.string.onBoarding_skip_button),
                    onClick = {
                        onClick()
                    },
                    masterDesignArgs = masterDesignArgs,
                    moduleDesignArgs = design
                )
            } else {
                MainButton(
                    buttonText = stringResource(id = R.string.onBoarding_next_settings),
                    onClick = {
                        val intent = Intent().apply {
                            action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                            putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                        }
                        context.startActivity(intent)
                    },
                    masterDesignArgs = masterDesignArgs,
                    moduleDesignArgs = design
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