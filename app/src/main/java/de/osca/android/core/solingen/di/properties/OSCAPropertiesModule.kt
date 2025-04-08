package de.osca.android.core.solingen.di.properties

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.osca.android.core.solingen.OSCAProperties
import de.osca.android.essentials.domain.entity.android.AppProperties
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class OSCAPropertiesModule {

    @Singleton
    @Provides
    fun providesAppProperties(): AppProperties {
        return OSCAProperties
    }
}