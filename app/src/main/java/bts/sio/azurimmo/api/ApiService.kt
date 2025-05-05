import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Reparation
import bts.sio.azurimmo.model.Locataire
import bts.sio.azurimmo.model.Contrat
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    // Service Batiments
        @GET("batiments") // Liste des Batiments
        suspend fun getBatiments(): List<Batiment>

        @GET("batiment/{batimentId}") // 1 Batiment
        suspend fun getBatiment(@Path("batimentId") batimentId: Int): Batiment

        @POST("batiment")
        suspend fun addBatiment(@Body batiment: Batiment): Batiment

        @PUT("batiment")
        suspend fun editBatiment(@Body batiment: Batiment): Batiment

        @DELETE("batiment/{id}")
        suspend fun deleteBatiment(@Body batiment: Int): Batiment

    // Service Appartements
        @GET("appartements") // Liste des Appartements
        suspend fun getAppartements(): List<Appartement>

        @GET("appartement/{id}") // 1 Appartement
        suspend fun getAppartement(@Path("id") id: Int): Appartement

        @GET("appartements/batiment/{batimentId}") // Liste des Appartements d'1 Batiment
        suspend fun getAppartementsByBatimentId(@Path("batimentId") batimentId: Int): List<Appartement>

    // Service Reparations
        @GET("reparations") // Liste des Réparations
        suspend fun getReparations(): List<Reparation>

    // Service Reparations
        @GET("locataires") // Liste des Locataires
        suspend fun getLocataires(): List<Locataire>

    // Service Reparations
        @GET("contrats") // Liste des Contrats
        suspend fun getContrats(): List<Contrat>

}