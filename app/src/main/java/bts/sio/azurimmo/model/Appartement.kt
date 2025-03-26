package bts.sio.azurimmo.model

data class Appartement(
    val numero: Int,
    val surface: Double,
    val nbPiece: Int,
    val description: String,
    val batiment: Batiment?
)
