package bts.sio.azurimmo.model

data class Societe(
    val id: Int,
    val nom: String,
    val description: String,
    val type: Type?
)
