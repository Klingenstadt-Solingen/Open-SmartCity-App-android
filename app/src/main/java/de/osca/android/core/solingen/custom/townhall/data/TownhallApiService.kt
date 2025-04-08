package de.osca.android.core.solingen.custom.townhall.data

import de.osca.android.core.solingen.custom.townhall.domain.TownhallData
import retrofit2.Response
import retrofit2.http.GET

interface TownhallApiService {

    @GET("classes/TownhallMenu")
    suspend fun getTownhallData(): Response<List<TownhallData>>
}