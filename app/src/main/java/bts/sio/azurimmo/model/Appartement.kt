package bts.sio.azurimmo.model

data class Appartement(
    val id: Int,
    val numero: Int,
    val surface: Double,
    val nbpieces: Int,
    val description: String,
    val batiment: Batiment?
)
