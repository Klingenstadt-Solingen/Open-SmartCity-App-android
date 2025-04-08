package de.osca.android.core.solingen

import android.content.Context
import androidx.navigation.NavBackStackEntry
import de.osca.android.district.core.util.getDistrictRouteString
import org.matomo.sdk.Matomo
import org.matomo.sdk.TrackerBuilder
import org.matomo.sdk.extra.TrackHelper

class MatomoExtension(context: Context, siteId: Int) {
    private var matomoTracker =
        TrackerBuilder.createDefault(BuildConfig.MATOMO_URL, siteId)
            .build(Matomo.getInstance(context))

    fun track(backStackEntry: NavBackStackEntry) {
        backStackEntry.destination.route?.let { routeString ->
            val trackRoute: String? =
                if (routeString.startsWith("de.osca.android.district")) {
                    getDistrictRouteString(routeString)
                } else {
                    routeString.split("?").first()
                }

            trackRoute?.let { route ->
                TrackHelper.track().screen(route).with(matomoTracker)
            }
        }
    }
}
