package de.osca.android.core.solingen

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.sentry.SentryOptions
import io.sentry.android.core.SentryAndroid

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // initializes sentry
        if (OSCAProperties.isSentryEnabled) {
            SentryAndroid.init(this) {
                it.dsn = BuildConfig.SENTRY_DSN
                it.beforeSend =
                    SentryOptions.BeforeSendCallback { event, _ ->
                        // don't send in debug
                        if (BuildConfig.DEBUG) {
                            null
                        } else {
                            event
                        }
                    }
            }
        }
    }
}
