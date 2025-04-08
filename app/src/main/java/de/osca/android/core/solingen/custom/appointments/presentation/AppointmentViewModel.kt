package de.osca.android.core.solingen.custom.appointments.presentation

import androidx.compose.runtime.mutableStateListOf
import dagger.hilt.android.lifecycle.HiltViewModel
import de.osca.android.core.solingen.custom.appointments.data.AppointmentApiService
import de.osca.android.core.solingen.custom.appointments.entity.Appointment
import de.osca.android.core.solingen.custom.appointments.presentation.args.AppointmentDesignArgs
import de.osca.android.essentials.presentation.base.BaseViewModel
import de.osca.android.essentials.utils.extensions.displayContent
import de.osca.android.essentials.utils.extensions.loading
import de.osca.android.essentials.utils.extensions.resetWith
import de.osca.android.networkservice.utils.RequestHandler
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class AppointmentViewModel @Inject constructor(
    val appointmentDesignArgs: AppointmentDesignArgs,
    private val appointmentApiService: AppointmentApiService,
    private val requestHandler: RequestHandler
) : BaseViewModel() {
    val appointments = mutableStateListOf<Appointment>()

    fun initialize(ref: String? = null) {
        wrapperState.loading()

        fetchAppointments(ref)
    }

    fun fetchAppointments(ref: String?): Job = launchDataLoad {
        val result = requestHandler.makeRequest(appointmentApiService::getAppointmentData) ?: emptyList()
        val allAppointments = result.filter { it.enabled && it.visible && it.ref == ref }.sortedBy { it.position }
        appointments.resetWith(allAppointments)

        wrapperState.displayContent()
    }
}