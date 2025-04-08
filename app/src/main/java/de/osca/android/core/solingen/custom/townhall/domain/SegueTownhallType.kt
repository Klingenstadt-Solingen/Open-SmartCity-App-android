package de.osca.android.core.solingen.custom.townhall.domain

import de.osca.android.contact.navigation.ContactNavItems
import de.osca.android.core.solingen.custom.appointments.navigation.AppointmentNavItems
import de.osca.android.defect.navigation.DefectNavItems
import de.osca.android.essentials.presentation.nav_items.EssentialsNavItems
import de.osca.android.waste.domain.entity.WasteData
import de.osca.android.waste.domain.misc.WasteHelper
import de.osca.android.waste.navigation.WasteNavItems

object SegueTownhallType {
    fun getRoute(
        segue: String?,
        url: String? = null,
        title: String? = null,
    ): String {
        return when (segue) {
            "defect" -> DefectNavItems.DefectNavItem.route
            "waste" -> {
                WasteHelper.wasteData = WasteData()
                WasteNavItems.WasteNavItem.route
            }

            "TableMenu-termin" -> AppointmentNavItems.AppointmentNavItem.route
            "contact" -> ContactNavItems.ContactFormNavItem.route
            "http" ->
                EssentialsNavItems.DirectWebViewNavItem(
                    url = url ?: "",
                    displayedTitle = title ?: "",
                ).route

            else -> EssentialsNavItems.WebViewNavItem.route
        }
    }
}
