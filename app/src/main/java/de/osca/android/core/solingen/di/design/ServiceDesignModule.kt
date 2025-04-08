package de.osca.android.core.solingen.di.design

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.osca.android.core.solingen.R
import de.osca.android.core.solingen.custom.services.presentation.args.ServiceDesignArgs
import de.osca.android.core.solingen.ui.theme.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceDesignModule {

    @Singleton
    @Provides
    fun providesServiceDesignArgs(): ServiceDesignArgs {
        return object : ServiceDesignArgs {
            /**
             * these are the values that must be set
             * this is used by the module and all screens with the same viewModel
             */
            override val textAlign: TextAlign? = null
            override val cardHeight: Dp = 200.dp
            override val constraintWidth: Dp? = null
            override val useImagePadding: Boolean = false
            override val externalLinkIconColor: Color = CLR_TextCard()
            override val externalLinkTextColor: Color = CLR_TextHint()
            override val previewCountForWidget: Int = 2
            /**
             * -> overrides that must be set
             */
            override val vShowMoreOption: Boolean = true
            override val vModuleTitle: Int = R.string.services_title
            override val vWidgetShowMoreOption: Boolean = true
            override val vIsWidgetVisible: Boolean = true
            override val vWidgetTitle: Int = R.string.services_title
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
            override val mWidgetShowLessText: Int? = null
            override val mWidgetShowMoreText: Int? = null
            override val mWidgetHeaderTextColor: Color? = null
            override val mWidgetShowMoreTextColor: Color? = null
            override val mIsStatusBarWhite: Boolean? = null
        }
    }
}