package de.osca.android.core.solingen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import de.osca.android.essentials.presentation.MainActivitySharedViewModel
import de.osca.android.essentials.presentation.base.BaseViewModel
import de.osca.android.essentials.presentation.component.bottom_navigation.OSCABottomNavigation
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs

@Composable
fun AppWrapper(
    baseViewModel: BaseViewModel = hiltViewModel(),
    masterDesignArgs: MasterDesignArgs = baseViewModel.defaultDesignArgs,
    sharedViewModel: MainActivitySharedViewModel,
    navController: NavHostController,
    content: @Composable () -> Unit
) {
    Surface(
        color = masterDesignArgs.mScreenBackColor
    ) {
        val topAppBarSize by remember { sharedViewModel.topBarSize }
        sharedViewModel.currentContentPaddings.value = WindowInsets.statusBars.only(WindowInsetsSides.Top).add(
            WindowInsets(top = topAppBarSize)
        ).asPaddingValues()

        Column(
            modifier = Modifier
                .navigationBarsPadding()
        ) {
            Scaffold(
                bottomBar = {
                    if (sharedViewModel.isBottomNavVisible.value) {
                        OSCABottomNavigation(
                            navController = navController,
                            masterDesignArgs = masterDesignArgs,
                            sharedViewModel = sharedViewModel
                        )
                    }
                }
            ) { innerPadding ->
                Box(modifier = Modifier
                    .padding(innerPadding)
                ) {
                    content()
                }
            }
        }
    }
}