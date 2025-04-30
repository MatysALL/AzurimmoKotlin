package bts.sio.azurimmo.model

data class Appartement(
    val id: Int,
    val numero: String,
    val surface: Number,
    val nbPiece: Int,
    val description: String,
    val batiment: Batiment?
)
