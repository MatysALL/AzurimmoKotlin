package bts.sio.azurimmo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import bts.sio.azurimmo.model.Locataire  // Mise à jour de l'import

class LocataireViewModel : ViewModel() {
    private val _locataires = MutableStateFlow<List<Locataire>>(emptyList())
    val locataires: StateFlow<List<Locataire>> = _locataires

    init {
        getLocataires()
    }

    private fun getLocataires() {
        viewModelScope.launch {
            _locataires.value = listOf(
                Locataire(1, "ALLANET", "Matys", 0,"matysallanet@gmail.com"),
                Locataire(2, "DENIEL", "Théo",0,"Théo@gmail.com"),
                Locataire(3, "LEROSSIGNOLE", "Baptiste",0,"Baptiste@gmail.com"),
                Locataire(4, "MOUCHARD", "Mathieu",0,"Mathieu@gmail.com")
            )
        }
    }
}
