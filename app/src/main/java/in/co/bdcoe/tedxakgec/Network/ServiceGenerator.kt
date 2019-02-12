package `in`.co.bdcoe.tedxakgec.Network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by rishi on 12/2/19.
 */
class ServiceGenerator {

    companion object {

        private val builder: OkHttpClient.Builder  =  OkHttpClient.Builder()

        private val logging: HttpLoggingInterceptor =  HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        val log  = builder.interceptors().add(logging)

        private val client: OkHttpClient = builder.build()

        private val tedxAkgecBuilder: Retrofit.Builder = Retrofit.Builder()
                .baseUrl("https://tedx-akgec.herokuapp.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
        private val retrofit: Retrofit = tedxAkgecBuilder.build()

        fun <S> createTedxAkgecService(serviceClass: Class<S>):S {
            return retrofit.create(serviceClass)
        }

    }

}