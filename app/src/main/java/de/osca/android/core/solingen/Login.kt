package de.osca.android.core.solingen

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import com.parse.Parse
import com.parse.ParseAnonymousUtils
import com.parse.ParseException
import com.parse.ParseInstallation
import com.parse.ParseObject
import com.parse.ParseUser
import de.osca.android.district.core.data.boundaries.ParseObjectRegistration
import de.osca.android.environment.domain.boundaries.EnvironmentParseRegistration
import de.osca.android.essentials.domain.entity.ParsePOI
import de.osca.android.essentials.domain.entity.push_notification.NotificationChannel
import de.osca.android.essentials.domain.entity.server.ParseInfo
import de.osca.android.essentials.utils.extensions.addIfNotContains
import java.io.File

object Login {
    /**
     * ANONYMOUS LOGIN (USER / SESSION)
     */
    fun anonymousLogIn(
        context: Context,
        isLoggedIn: (user: ParseUser) -> Unit,
        isError: (err: ParseException) -> Unit,
        environmentParseRegistration: EnvironmentParseRegistration,
        parseRegistrations: Set<ParseObjectRegistration>,
        testEx: ParseException? = null,
    ) {
        val parseInfo = OSCAProperties.parseInfo

        try {
            // initialize parse classes before parse server
            ParseObject.registerSubclass(ParsePOI::class.java)
            parseRegistrations.forEach {
                it.registerSubclasses()
            }
            environmentParseRegistration.registerSubclasses()
            // initialize Parse-Server
            println("anoint")
            initParse(context, parseInfo)
            println("anoinitdone")
        } catch (ex: ParseException) {
            isError(ex)
        }

        try {
            // Login with anonymous user
            login(
                testEx = testEx,
                onSuccess = isLoggedIn,
                onException = { e ->

                    handleError(
                        context = context,
                        parseInfo = parseInfo,
                        exception = e,
                        onSuccess = isLoggedIn,
                        onException = isError,
                    )
                },
            )
        } catch (ex: ParseException) {
            isError(ex)
        }
    }

    /**
     * Handles the potential errors on a User Login.
     *
     * Parse tries to reuse the last logged-in anonymous user.
     * After changing the target server (e.g. through .env config), that user is invalid.
     * Changing the target Server breaks the ParseInstallation which causes another Exception that prevents the use of the App.
     * Parse Documentation says (somewhere) that the server address must be backed by the same Database Server,
     * otherwise changing the server address is not allowed
     */
    private fun handleError(
        context: Context,
        parseInfo: ParseInfo,
        exception: ParseException,
        onSuccess: (user: ParseUser) -> Unit,
        onException: (e: ParseException) -> Unit,
        ignore: List<Int> = emptyList(),
    ) {
        // if we already had that exception, don't fall info a loop and break
        if (ignore.contains(exception.code)) onException(exception)
        val ignore = ignore.plus(exception.code)

        // if the session is invalid, try to get a new session
        if (exception.code == ParseException.INVALID_SESSION_TOKEN) {
            // logout possible invalid user and retry
            ParseUser.logOut()

            login(
                onSuccess = onSuccess,
                onException = { e ->
                    handleError(
                        context,
                        parseInfo,
                        e,
                        onSuccess,
                        onException,
                        ignore,
                    )
                },
            )
        } else if (exception.code == ParseException.OBJECT_NOT_FOUND) {
            // This exception will most likely come up (while login) if the installation is not found

            var parseFilesDir = context.filesDir.path + "/com.parse"

            // remove backup installation file (parse wants to abandon this file in the future but needs it for backwards compatibility)
            val installationIdFile =
                File(parseFilesDir + "/installationId")

            // remove the installation information
            val currentInstallationFile =
                File(parseFilesDir + "/currentInstallation")

            installationIdFile.delete()
            currentInstallationFile.delete()

            Parse.destroy()

            initParse(context, parseInfo)

            login(
                onSuccess = onSuccess,
                onException = { e ->
                    handleError(
                        context,
                        parseInfo,
                        e,
                        onSuccess,
                        onException,
                        ignore,
                    )
                },
            )
        } else {
            Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()

            onException(exception)
        }
    }

    private fun initParse(
        context: Context,
        config: ParseInfo,
    ) {
        Parse.initialize(
            Parse.Configuration
                .Builder(context)
                .applicationId(config.appId)
                .clientKey(config.key)
                .server(config.endpoint)
                .build(),
        )
    }

    private fun login(
        onSuccess: (user: ParseUser) -> Unit,
        onException: (e: ParseException) -> Unit,
        testEx: ParseException? = null,
    ) {
        try {
            println("anonylogi")
            ParseAnonymousUtils.logIn { user, err ->
                println("parserespons")
                val error = testEx ?: err

                if (error != null) {
                    println("onExceptin")
                    onException(error)
                } else {
                    // after login create installation with same id as sessionInstallationId
                    println("regis")
                    registerInstallation(user, onSuccess, onException)
                    println("regisdone")
                }
            }
        } catch (e: ParseException) {
            onException(e)
        }
    }

    /**
     * SESSION REVOKE
     */
    fun revokeSession(wasRevoked: () -> Unit) {
        ParseUser.logOutInBackground {
            if (it == null) {
                wasRevoked()
            } else {
            }
        }
    }

    /**
     * INSTALLATION PART
     */
    fun registerInstallation(
        user: ParseUser,
        isLoggedIn: (user: ParseUser) -> Unit,
        onException: ((exception: ParseException) -> Unit),
    ) {
        println("getcur")
        val installation = ParseInstallation.getCurrentInstallation()
        println("gettoke")
        getFCMToken(
            onGetFCM = { theToken ->
                installation.deviceToken = theToken
                installation.pushType = "gcm"
                ParseInstallation.saveAllInBackground(listOf(installation)) { pex ->
                    if (pex == null) {
                        isLoggedIn(user)
                    } else {
                        onException(pex)
                    }
                }
            },
            onError = {
                ParseInstallation.saveAllInBackground(listOf(installation)) { pex ->
                    if (pex == null) {
                        isLoggedIn(user)
                    } else {
                        onException(pex)
                    }
                }
            },
        )
    }

    fun getFCMToken(
        onGetFCM: (token: String) -> Unit,
        onError: () -> Unit,
    ) {
        FirebaseMessaging
            .getInstance()
            .token
            .addOnSuccessListener { token: String ->
                if (!TextUtils.isEmpty(token)) {
                    // Log.d(TAG, "retrieve token successful : $token")
                } else {
                    // Log.w(TAG, "token should not be null...")
                }
            }.addOnFailureListener { e: java.lang.Exception? -> }
            .addOnCanceledListener {}
            .addOnCompleteListener { task: Task<String> ->
                if (task.isSuccessful) {
                    // Log.v(TAG, "This is the token : " + task.result)
                    onGetFCM(task.result)
                } else {
                    onError()
                }
            }
    }

    fun updateChannels(
        channel: NotificationChannel,
        subscribe: Boolean,
    ) {
        val installation = ParseInstallation.getCurrentInstallation()

        val subscriptions =
            (installation.get("channels") as MutableList<String>?) ?: mutableListOf<String>()

        when (subscribe) {
            true -> subscriptions.addIfNotContains(channel.name)
            false -> subscriptions.remove(channel.name)
        }

        installation.put("channels", subscriptions)

        ParseInstallation.saveAllInBackground(listOf(installation)) { pex ->
            if (pex == null) {
                // updated
            }
        }
    }

    fun getInstallation(): ParseInstallation = ParseInstallation.getCurrentInstallation()

    fun getSubscribedTo(channel: NotificationChannel): Boolean {
        val installation = ParseInstallation.getCurrentInstallation()

        return (installation.get("channels") as MutableList<String>?)?.contains(channel.name)
            ?: false
    }
}
