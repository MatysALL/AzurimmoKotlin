package bts.sio.azurimmo.model

import java.time.LocalDate
import java.util.Date

data class Reparation(
    val id: Int,
    val dateReparation: LocalDate,
    val description: String,
    val appartement: Appartement?,
    val societe: Societe?
)
