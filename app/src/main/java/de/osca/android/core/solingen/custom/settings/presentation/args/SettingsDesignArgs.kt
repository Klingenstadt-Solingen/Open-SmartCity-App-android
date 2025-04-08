package de.osca.android.core.solingen.custom.settings.presentation.args

import de.osca.android.essentials.presentation.component.design.ModuleDesignArgs

interface SettingsDesignArgs : ModuleDesignArgs {
    val applicationId: String
    val buildVType: String
    val buildSdkMin: Int
    val buildSdkTarget: Int
    val buildSdkCompile: Int
    val buildVersion: String
}