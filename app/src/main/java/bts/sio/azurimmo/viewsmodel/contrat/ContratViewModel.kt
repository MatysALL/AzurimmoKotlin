package bts.sio.azurimmo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import bts.sio.azurimmo.model.Contrat  // Mise à jour de l'import
import java.time.LocalDate

class ContratViewModel : ViewModel() {
    private val _contrats = MutableStateFlow<List<Contrat>>(emptyList())
    val contrats: StateFlow<List<Contrat>> = _contrats

    init {
        getLocataires()
    }

    private fun getLocataires() {
        viewModelScope.launch {
            _contrats.value = listOf(
                Contrat(
                    id = 1,
                    datedebut = LocalDate.parse("2022-10-05"),
                    datefin = LocalDate.parse("2023-10-05"),
                    locataire = null, // Remplacez `null` par un objet Locataire valide si nécessaire
                    appartement = null // Remplacez `null` par un objet Appartement valide si nécessaire
                ),
                Contrat(
                    id = 2,
                    datedebut = LocalDate.parse("2022-10-07"),
                    datefin = LocalDate.parse("2023-10-07"),
                    locataire = null,
                    appartement = null
                ),
                Contrat(
                    id = 3,
                    datedebut = LocalDate.parse("2022-12-21"),
                    datefin = LocalDate.parse("2023-12-21"),
                    locataire = null,
                    appartement = null
                )
            )
        }
    }
}
