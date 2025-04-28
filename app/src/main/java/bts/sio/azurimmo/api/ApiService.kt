import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Reparation
import bts.sio.azurimmo.model.Locataire
import bts.sio.azurimmo.model.Contrat
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    // Service Batiments
        @GET("batiments")
        suspend fun getBatiments(): List<Batiment>

        @GET("batiments/{id}")
        suspend fun getBatiment(@Path("id") id: Int): Batiment

        @GET("batiment/{batimentId}")
        suspend fun getBatiment(): List<Batiment>

    // Service Appartements
        @GET("appartements")
        suspend fun getAppartements(): List<Appartement>

        @GET("appartements/{numero}")
        suspend fun getAppartement(@Path("numero") id: Int): Appartement

        @GET("appartements/batiment/{batimentId}")
        suspend fun getAppartementsByBatimentId(@Path("batimentId") batimentId: Int): List<Appartement>

    // Service Reparations
        @GET("reparations")
        suspend fun getReparations(): List<Reparation>

    // Service Reparations
        @GET("locataires")
        suspend fun getLocataires(): List<Locataire>

    // Service Reparations
        @GET("contrats")
        suspend fun getContrats(): List<Contrat>

}