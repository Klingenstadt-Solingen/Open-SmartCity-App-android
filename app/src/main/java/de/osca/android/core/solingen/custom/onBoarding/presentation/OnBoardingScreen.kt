package de.osca.android.core.solingen.custom.onBoarding.presentation

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import de.osca.android.core.solingen.custom.dashboard.navigation.DashboardNavItems
import de.osca.android.core.solingen.custom.onBoarding.presentation.components.PageIdentifierElement
import de.osca.android.core.solingen.custom.onBoarding.presentation.pages.PageFiveScreen
import de.osca.android.core.solingen.custom.onBoarding.presentation.pages.PageFourScreen
import de.osca.android.core.solingen.custom.onBoarding.presentation.pages.PageOneScreen
import de.osca.android.core.solingen.custom.onBoarding.presentation.pages.PageSixScreen
import de.osca.android.core.solingen.custom.onBoarding.presentation.pages.PageThreeScreen
import de.osca.android.core.solingen.custom.onBoarding.presentation.pages.PageTwoScreen
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.screen_wrapper.ScreenWrapper
import de.osca.android.essentials.utils.extensions.SetSystemStatusBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    navController: NavController,
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel(),
    masterDesignArgs: MasterDesignArgs = onBoardingViewModel.defaultDesignArgs
) {
    val design = onBoardingViewModel.onBoardingDesignArgs

    LaunchedEffect(Unit) {
        onBoardingViewModel.initializeServerConfig()
    }

    SetSystemStatusBar(
        !(design.mIsStatusBarWhite ?: masterDesignArgs.mIsStatusBarWhite), Color.Transparent
    )

    ScreenWrapper(
        withTopBar = false,
        screenWrapperState = onBoardingViewModel.wrapperState,
        masterDesignArgs = masterDesignArgs,
        moduleDesignArgs = design
    ) {
        Pager(
            onBoardingViewModel = onBoardingViewModel,
            navController = navController
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Pager(
    onBoardingViewModel: OnBoardingViewModel,
    navController: NavController,
    masterDesignArgs: MasterDesignArgs = onBoardingViewModel.defaultDesignArgs
) {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { 6 })

    val navigateForward = {
        onClickNextPageBtn(
            navController,
            pagerState,
            scope
        )
    }

    val navigateBackward = {
        onClickPreviousPageBtn(
            navController,
            pagerState,
            scope
        )
    }

    Box {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(top = 64.dp, bottom = 40.dp).fillMaxSize()
        ) { pageElement ->
            Box(modifier = Modifier
                .padding(horizontal = 32.dp)
            ) {
                when (pageElement) {
                    0 -> PageOneScreen( // WELCOME
                        onBoardingViewModel = onBoardingViewModel,
                        onClick = { navigateForward() }
                    )
                    1 -> PageTwoScreen( // DATA PRIVACY
                        onBoardingViewModel = onBoardingViewModel,
                        onClick = { navigateForward() },
                        onClickBack = { navigateBackward() }
                    )
                    2 -> PageThreeScreen( // DATA PRIVACY TEXT
                        onBoardingViewModel = onBoardingViewModel,
                        onClick = { navigateForward() },
                        onClickBack = { navigateBackward() }
                    )
                    3 -> PageFourScreen( // NOTIFICATIONS
                        onBoardingViewModel = onBoardingViewModel,
                        onClick = { navigateForward() },
                        onClickBack = { navigateBackward() }
                    )
                    4 -> PageFiveScreen( // LOCATION
                        onBoardingViewModel = onBoardingViewModel,
                        onClick = { navigateForward() },
                        onClickBack = { navigateBackward() }
                    )
                    5 -> PageSixScreen( // LOS GEHT'S
                        onBoardingViewModel = onBoardingViewModel,
                        onClick = {
                            navigateForward()
                            onBoardingViewModel.setIsFirstStart(false)
                        },
                        onClickBack = { navigateBackward() }
                    )
                }
            }
        }
        PageIdentifierElement(
            dotCount = pagerState.pageCount,
            selectedDot = pagerState.currentPage,
            masterDesignArgs = masterDesignArgs,
            modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .padding(bottom = 16.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun onClickNextPageBtn(
    navController: NavController,
    pagerState: PagerState,
    scope: CoroutineScope
) {
    if (pagerState.currentPage < pagerState.pageCount - 1) {
        scope.launch {
            pagerState.animateScrollToPage(
                pagerState.currentPage + 1,
                animationSpec = tween(600)
            )
        }
    } else {
        navController.navigate(DashboardNavItems.DashboardNavItem.route)
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun onClickPreviousPageBtn(
    navController: NavController,
    pagerState: PagerState,
    scope: CoroutineScope,
) {
    if (pagerState.currentPage > 0) {
        scope.launch {
            pagerState.animateScrollToPage(
                pagerState.currentPage - 1,
                animationSpec = tween(600)
            )
        }
    } else {
        navController.navigate(DashboardNavItems.DashboardNavItem.route)
    }
}