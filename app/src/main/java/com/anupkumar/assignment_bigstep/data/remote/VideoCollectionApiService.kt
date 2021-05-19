package  com.anupkumar.assignment_bigstep.data.remote

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface VideoCollectionApiService {

    @GET("search?term=Michael+jackson&amp;media=musicVideo")
    fun getVideoCollection(): Observable<VideoCollectionResponse?>?
}