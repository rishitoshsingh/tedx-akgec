package `in`.co.bdcoe.tedxakgec.Network

import `in`.co.bdcoe.tedxakgec.POJOs.TedxResult
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by rishi on 12/2/19.
 */
interface TedxAkgecClient {

    @GET("api/get-all")
    fun getAll(): Call<TedxResult>

}