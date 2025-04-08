package de.osca.android.core.solingen.custom.dashboard.presentation

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import de.osca.android.core.solingen.custom.dashboard.entity.WidgetModules
import de.osca.android.core.solingen.custom.dashboard.presentation.args.DashboardDesignArgs
import de.osca.android.essentials.presentation.base.BaseViewModel
import de.osca.android.essentials.utils.extensions.displayContent
import de.osca.android.essentials.utils.extensions.resetWith
import de.osca.android.waste.domain.boundaries.WasteRepository
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    val dashboardDesignArgs: DashboardDesignArgs,
    private val wasteRepository: WasteRepository
) : BaseViewModel() {
    //var saveFileObject = SaveFileObject()
    val dashboardWidgets = mutableStateListOf<WidgetModules>()

    val headerImage by mutableIntStateOf(dashboardDesignArgs.switchingHeaderImages.random())
    var isShowWasteDashboardEnabled by mutableStateOf(false)

    init {
        launchDataLoad {
            isShowWasteDashboardEnabled = wasteRepository.isShowWasteDashboardEnabled()
        }
    }

    fun initializeSaveFile(context: Context) {
       // saveFileObject = FileSystem.readSaveFileObject(context)
        loadDashboard()
    }

    private fun loadDashboard() {
     /*   if(saveFileObject.dashboardConfig.isNotEmpty()) {
            dashboardWidgets.resetWith(saveFileObject.dashboardConfig.map { WidgetModules.valueOf(it) })
        } else {

      */
        dashboardWidgets.resetWith(dashboardDesignArgs.widgetsToShow)
        wrapperState.displayContent()
    }

    /*
    fun saveDashboard(context: Context, saveList: List<WidgetModules>) {
        saveFileObject.dashboardConfig = saveList.map { it.name }
        FileSystem.writeSaveFileObject(context, saveFileObject)
    }
     */
}