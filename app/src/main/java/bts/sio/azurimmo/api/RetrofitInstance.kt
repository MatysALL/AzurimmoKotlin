package bts.sio.azurimmo.api

import ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:8080/"
    //private const val BASE_URL = "https://172.20.177.251:8080/matysazurimmo_war/"
    //private const val BASE_URL = "https://prodtomcat.inforostand14.net:8080/matysazurimmo_war/"
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
