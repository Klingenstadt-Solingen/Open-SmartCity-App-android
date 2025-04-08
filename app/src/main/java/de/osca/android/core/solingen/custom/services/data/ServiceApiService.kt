package de.osca.android.core.solingen.custom.services.data

import de.osca.android.core.solingen.custom.services.domain.ServiceData
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApiService {

    @GET("classes/ServiceMenu")
    suspend fun getServiceData(): Response<List<ServiceData>>
}