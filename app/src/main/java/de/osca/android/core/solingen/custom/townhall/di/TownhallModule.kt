package de.osca.android.core.solingen.custom.townhall.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.osca.android.core.solingen.custom.townhall.data.TownhallApiService
import de.osca.android.essentials.data.client.OSCAHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TownhallModule {

    @Singleton
    @Provides
    fun townhallApiService(oscaHttpClient: OSCAHttpClient): TownhallApiService =
        oscaHttpClient.create(TownhallApiService::class.java)

}