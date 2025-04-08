package de.osca.android.core.solingen.di.design

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.osca.android.core.solingen.R
import de.osca.android.core.solingen.custom.onBoarding.presentation.args.OnBoardingDesignArgs
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class OnBoardingDesignModule {

    @Singleton
    @Provides
    fun providesOnBoardingDesignArgs(): OnBoardingDesignArgs {
        return object : OnBoardingDesignArgs {
            /**
             * these are the values that must be set
             * this is used by the module and all screens with the same viewModel
             */
            // PAGE 1
            override val cityLogo: Int = R.drawable.mensch_solingen_round
            override val cityPrefix: Int? = R.string.onBoarding_screen_one_element_0
            override val cityName: Int = R.string.city_name
            override val contentPage1: List<Int> = listOf(
                R.string.onBoarding_screen_one_element_1,
                R.string.onBoarding_screen_one_element_2
            )
            // PAGE 2
            override val dataPrivacyImage: Int? = R.drawable.privacyprotection
            override val dataPrivacyTitle: Int = R.string.onBoarding_screen_two_element_0
            override val contentPage2: List<Int> = listOf(
                R.string.onBoarding_screen_two_element_1,
                R.string.onBoarding_screen_two_element_2
            )
            // PAGE 4
            override val notificationImage: Int? = R.drawable.notifications
            override val notificationTitle: Int = R.string.onBoarding_screen_four_element_0
            override val contentPage3: List<Int> = listOf(
                R.string.onBoarding_screen_four_element_1
            )
            // PAGE 5
            override val locationImage: Int? = R.drawable.location
            override val locationTitle: Int = R.string.onBoarding_screen_five_element_0
            override val contentPage4: List<Int> = listOf(
                R.string.onBoarding_screen_five_element_1
            )
            // PAGE 6
            override val welcomeImage: Int? = R.drawable.welcome
            override val welcomeTitle: Int = R.string.onBoarding_screen_six_element_0
            override val contentPage5: List<Int> = listOf(
                R.string.onBoarding_screen_six_element_1,
                R.string.onBoarding_screen_six_element_2
            )
            /**
             * -> overrides that must be set
             */
            override val vShowMoreOption: Boolean = true
            override val vModuleTitle: Int = R.string.global_webview_title
            /**
             * use overrides to overwrite the masterDesignArgs for this specific module
             * -> null if you want to keep the default values
             * -> every module extends ModuleDesignArgs to prevent code repetition
             */
            override val mTopBarBackColor: Color? = null
            override val mBottomBarBackColor: Color? = null
            override val mStatusBarBackColor: Color? = null
            override val mCardBackColor: Color? = null
            override val mMenuBackColor: Color? = null
            override val mSheetBackColor: Color? = null
            override val mScreenBackColor: Color? = null
            override val mTopBarTextColor: Color? = null
            override val mBottomBarTextColor: Color? = null
            override val mStatusBarTextColor: Color? = null
            override val mCardTextColor: Color? = null
            override val mMenuTextColor: Color? = null
            override val mSheetTextColor: Color? = null
            override val mScreenTextColor: Color? = null
            override val mHintTextColor: Color? = null
            override val mTextInCard: Boolean? = null
            override val mRootCardSpacing: Dp? = null
            override val mRootBoarderSpacing: Dp? = null
            override val mCardContentPadding: Dp? = null
            override val mConstrainHeight: Dp? = null
            override val mCardElevation: Dp? = null
            override val mSheetElevation: Dp? = null
            override val mShowLessText: Int? = null
            override val mShowMoreText: Int? = null
            override val mHeaderTextColor: Color? = null
            override val mShowMoreTextColor: Color? = null
            override val mShapeCard: Dp? = null
            override val mShapeBottomSheet: Shape? = null
            override val mShapeTopSheet: Shape? = null
            override val mShapeSheet: Shape? = null
            override val mButtonBackgroundColor: Color? = null
            override val mButtonContentColor: Color? = null
            override val mSwitchCheckedThumbColor: Color? = null
            override val mSwitchCheckedTrackColor: Color? = null
            override val mSwitchUncheckedThumbColor: Color? = null
            override val mSwitchUncheckedTrackColor: Color? = null
            override val mDropDownBorderColor: Color? = null
            override val mTextFieldFocusedBorderColor: Color? = null
            override val mTextFieldUnfocusedBorderColor: Color? = null
            override val mDialogsBackgroundColor: Color? = null
            override val mDialogsTextColor: Color? = null
            override val mDialogsBackColor: Color? = null
            override val mBorderSpace: Dp? = null
            override val mContentPaddingForMiniCards: Dp? = null
            override val mRoundIconSize: Dp? = null
            override val mIsStatusBarWhite: Boolean? = null
        }
    }
}