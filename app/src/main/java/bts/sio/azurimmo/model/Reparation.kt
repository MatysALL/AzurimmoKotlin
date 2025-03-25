package bts.sio.azurimmo.model

import java.util.Date

data class Reparation(
    val id: Int,
    val description: String,
    val jour: String,
    val appartement: Appartement?,
    val societe: Societe?,
    val type: Type?
)
