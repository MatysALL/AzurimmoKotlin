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
                Locataire(1, "ALLANET", "Matys", "2006-11-25"),
                Locataire(2, "DENIEL", "Théo","2005-12-25"),
                Locataire(3, "LEROSSIGNOLE", "Baptiste","2004-01-25"),
                Locataire(4, "MOUCHARD", "Mathieu","2003-02-25")
            )
        }
    }
}
