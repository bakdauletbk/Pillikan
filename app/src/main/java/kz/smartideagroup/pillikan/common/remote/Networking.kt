package kz.smartideagroup.pillikan.common.remote


import kz.smartideagroup.pillikan.common.remote.interceptors.AuthInterceptor
import kz.smartideagroup.pillikan.common.factories.FixedConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Networking {

    fun createWithInterceptor(baseUrl: String): NetworkService {

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(AuthInterceptor())

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(builder.build())
            .addConverterFactory(FixedConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }

    fun create(baseUrl: String): NetworkService {

        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttp: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logger)

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }

}
