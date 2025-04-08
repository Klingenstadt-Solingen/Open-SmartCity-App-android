package de.osca.android.core.solingen.custom.services.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.osca.android.core.solingen.custom.services.data.ServiceApiService
import de.osca.android.essentials.data.client.OSCAHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Singleton
    @Provides
    fun serviceApiService(oscaHttpClient: OSCAHttpClient): ServiceApiService =
        oscaHttpClient.create(ServiceApiService::class.java)

}