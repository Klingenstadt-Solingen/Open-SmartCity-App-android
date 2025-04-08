package de.osca.android.core.solingen.custom.onBoarding.presentation.args

import de.osca.android.essentials.presentation.component.design.ModuleDesignArgs

interface OnBoardingDesignArgs : ModuleDesignArgs {
    // PAGE 1
    val cityLogo: Int
    val cityPrefix: Int?
    val cityName: Int
    val contentPage1: List<Int>

    // PAGE 2
    val dataPrivacyImage: Int?
    val dataPrivacyTitle: Int
    val contentPage2: List<Int>

    // PAGE 3

    // PAGE 4
    val notificationImage: Int?
    val notificationTitle: Int
    val contentPage3: List<Int>

    // PAGE 5
    val locationImage: Int?
    val locationTitle: Int
    val contentPage4: List<Int>

    // PAGE 6
    val welcomeImage: Int?
    val welcomeTitle: Int
    val contentPage5: List<Int>
}