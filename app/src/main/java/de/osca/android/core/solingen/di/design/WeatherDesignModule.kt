package de.osca.android.core.solingen.di.design

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.osca.android.core.solingen.R
import de.osca.android.core.solingen.ui.theme.CLR_BackgroundDialog
import de.osca.android.core.solingen.ui.theme.CLR_TextDialog
import de.osca.android.weather.presentation.args.WeatherDesignArgs
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WeatherDesignModule {

    @Singleton
    @Provides
    fun providesWeatherDesignArgs(): WeatherDesignArgs {
        return object : WeatherDesignArgs {
            /**
             * these are the values that must be set
             * this is used by the module and all screens with the same viewModel
             */
            override val columnCount: Int = 3
            override val mapCardHeight: Dp = 200.dp
            override val fillDummies: Boolean = false
            override val showRainDropIcon: Boolean = true
            override val showRainIntensity: Boolean = true
            override val mapZoomLevel: Float = 15f
            override val widgetTitleAppending: Int? = null
            override val weatherDigits: Int = 2
            override val showWeatherSymbol: Boolean = true
            override val mapStyle: Int? = R.raw.map_style_without_any
            /**
             * -> widget specific
             */
            override val vShowMoreOption: Boolean = true
            override val vIsWidgetVisible: Boolean = true
            override val vModuleTitle: Int = R.string.weather_title
            override val vWidgetShowMoreOption: Boolean = true
            override val vWidgetTitle: Int = R.string.dashboard_weather_in_city
            /**
             * use overrides to overwrite the masterDesignArgs for this specific module
             * -> null if you want to keep the default values
             * -> every module extends ModuleDesignArgs to prevent code repetition
             */
            override val mTopBarBackColor: Color? = CLR_BackgroundDialog()
            override val mBottomBarBackColor: Color? = null
            override val mStatusBarBackColor: Color? = null
            override val mCardBackColor: Color? = null
            override val mMenuBackColor: Color? = null
            override val mSheetBackColor: Color? = null
            override val mScreenBackColor: Color? = null
            override val mTopBarTextColor: Color? = CLR_TextDialog()
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
            override val mWidgetShowLessText: Int? = null
            override val mWidgetShowMoreText: Int? = null
            override val mWidgetHeaderTextColor: Color? = null
            override val mWidgetShowMoreTextColor: Color? = null
            override val mIsStatusBarWhite: Boolean? = true
        }
    }
}