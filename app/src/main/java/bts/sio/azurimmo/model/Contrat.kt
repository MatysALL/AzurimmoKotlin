package bts.sio.azurimmo.model

import java.time.LocalDate

data class Contrat(
    val id: Int,
    val datedebut: LocalDate,
    val datefin: LocalDate,
    val locataire: Locataire?,
    val appartement: Appartement?,
)
