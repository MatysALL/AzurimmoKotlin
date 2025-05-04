package bts.sio.azurimmo.api

import ApiService
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
object RetrofitInstance {
    //private const val BASE_URL = "http://10.0.2.2:8080/"
    //private const val BASE_URL = "https://172.20.177.251:8080/matysazurimmo_war/"
    private const val BASE_URL = "https://prodtomcat.inforostand14.net/matysazurimmo/"

    @RequiresApi(Build.VERSION_CODES.O)
    private val localDateDeserializer: JsonDeserializer<LocalDate> = JsonDeserializer { json, _, _ ->
        val dateStr = json.asString
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // Vérifie si le format correspond à celui de ton API
        LocalDate.parse(dateStr, formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(LocalDate::class.java, localDateDeserializer)
        .create()

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))  // Utilisation du Gson personnalisé
            .build()
            .create(ApiService::class.java)
    }

}
