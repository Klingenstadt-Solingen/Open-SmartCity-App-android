package de.osca.android.core.solingen.custom.appointments.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.osca.android.core.solingen.custom.appointments.data.AppointmentApiService
import de.osca.android.essentials.data.client.OSCAHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppointmentModule {

    @Singleton
    @Provides
    fun appointmentApiService(oscaHttpClient: OSCAHttpClient): AppointmentApiService =
        oscaHttpClient.create(AppointmentApiService::class.java)

}