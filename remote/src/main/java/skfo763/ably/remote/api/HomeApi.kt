package skfo763.ably.remote.api

import androidx.annotation.VisibleForTesting
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import skfo763.ably.remote.data.HomeApiResult

interface HomeApi {
    
    companion object {
        const val ENDPOINT = "home"
    }

    @VisibleForTesting
    fun getMoshi()

    @GET(ENDPOINT)
    fun getHomeData(): Single<HomeApiResult>

    @GET("$ENDPOINT/goods")
    fun getMoreGoods(
            @Query("lastId") lastId: Int? = null
    ): Single<HomeApiResult>

}