package de.osca.android.core.solingen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import de.osca.android.essentials.presentation.MainActivitySharedViewModel
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs

@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CityApp(
    sharedViewModel: MainActivitySharedViewModel,
    masterDesignArgs: MasterDesignArgs,
    navController: NavHostController,
) {
    val context = LocalContext.current

    LaunchedEffect(navController) {
        val siteId = BuildConfig.MATOMO_SITE_ID.toIntOrNull()

        if (BuildConfig.MATOMO_ENABLED == "true" &&
            BuildConfig.MATOMO_URL.isNotBlank() &&
            siteId != null
        ) {
            val matomoExtension = MatomoExtension(context, siteId)

            navController.currentBackStackEntryFlow.collect { backStackEntry ->
                matomoExtension.track(backStackEntry)
            }
        }
    }

    AppWrapper(
        sharedViewModel = sharedViewModel,
        navController = navController,
    ) {
        NavigationGraph(
            navController = navController,
            sharedViewModel = sharedViewModel,
            masterDesignArgs = masterDesignArgs,
        )
    }
}
