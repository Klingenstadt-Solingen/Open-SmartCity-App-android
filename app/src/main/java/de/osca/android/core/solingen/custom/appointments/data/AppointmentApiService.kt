package de.osca.android.core.solingen.custom.appointments.data

import de.osca.android.core.solingen.custom.appointments.entity.Appointment
import retrofit2.Response
import retrofit2.http.GET

interface AppointmentApiService {

    @GET("classes/TableMenu")
    suspend fun getAppointmentData(): Response<List<Appointment>>
}