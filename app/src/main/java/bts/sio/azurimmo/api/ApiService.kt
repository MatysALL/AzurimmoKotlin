import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Reparation
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    // Service Batiments
    @GET("/api/batiments/")
    suspend fun getBatiments(): List<Batiment>

    @GET("/api/batiments/{id}")
    suspend fun getBatiment(@Path("id") id: Int): Batiment

    @GET("/api/batiment/{batimentId}")
    suspend fun getBatiment(): List<Batiment>

    // Service Appartements
    @GET("/api/appartements/")
    suspend fun getAppartements(): List<Appartement>

    @GET("/api/appartements/batiment/{batimentId}")
    suspend fun getAppartementsByBatimentId(@Path("batimentId") batimentId: Int): List<Appartement>

    // Service Reparations
    @GET("/api/reparations/")
    suspend fun getReparations(): List<Reparation>

}