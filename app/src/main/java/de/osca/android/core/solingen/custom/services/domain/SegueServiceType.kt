package de.osca.android.core.solingen.custom.services.domain

import de.osca.android.coworking.navigation.CoworkingNavItems
import de.osca.android.district.event.navigation.EventNavItems
import de.osca.android.essentials.presentation.nav_items.EssentialsNavItems
import de.osca.android.jobs.navigation.JobsNavItems
import de.osca.android.mobility.navigation.MobilityNavItems
import de.osca.android.public_transport.navigation.PublicTransportNavItems
import de.osca.android.sgart.navigation.ArtNavItems

object SegueServiceType {

    fun getRoute(segue: String?, url: String? = null, title: String? = null): Any {
        return when(segue) {
            "coworking" -> CoworkingNavItems.CoworkingNavItem.route
            "jobs" -> JobsNavItems.JobsNavItem.route
            "events" -> EventNavItems.EventListNavItem
            "publicTransport" -> PublicTransportNavItems.PublicTransport.route
            "art" -> ArtNavItems.ArtFormNavItem.route
            "mobilitymonitor" -> MobilityNavItems.MobilityDashboardNavItem.route
            "http" -> EssentialsNavItems.DirectWebViewNavItem(
                url = url ?: "",
                displayedTitle = title ?: ""
            ).route
            else -> EssentialsNavItems.WebViewNavItem.route
        }
    }
}