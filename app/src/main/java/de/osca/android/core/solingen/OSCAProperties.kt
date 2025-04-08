package de.osca.android.core.solingen

import com.google.android.gms.maps.model.LatLng
import de.osca.android.essentials.domain.entity.android.AppProperties
import de.osca.android.essentials.domain.entity.server.ParseInfo
import de.osca.android.waste.domain.entity.push_notification.WasteNotificationChannel

object OSCAProperties : AppProperties {
    override val isMocked = false
    override val defaultLatLng: LatLng = LatLng(51.170208, 7.083141)
    override val coronaMapLink =
        "https://geoportal.solingen.de/buergerservice1/ressourcen/kml/maskenpflicht.kml"
    override val parseInfo =
        ParseInfo(
            endpoint = BuildConfig.PARSE_URL,
            key = BuildConfig.PARSE_CLIENT_KEY,
            appId = BuildConfig.PARSE_APP_ID,
        )
    override val availableNotificationChannels =
        listOf(
            WasteNotificationChannel,
        )

    // shows as "is always ..." once BuildConfig is created
    val isSentryEnabled =
        BuildConfig.SENTRY_ENABLED == "true" && !BuildConfig.SENTRY_DSN.isNullOrBlank()
}
