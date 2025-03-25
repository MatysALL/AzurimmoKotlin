package bts.sio.azurimmo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import bts.sio.azurimmo.model.Contrat  // Mise à jour de l'import

class ContratViewModel : ViewModel() {
    private val _contrats = MutableStateFlow<List<Contrat>>(emptyList())
    val contrats: StateFlow<List<Contrat>> = _contrats

    init {
        getLocataires()
    }

    private fun getLocataires() {
        viewModelScope.launch {
            _contrats.value = listOf(
                Contrat(1, "2022-10-05", "2023-10-05"),
                Contrat(2, "2022-10-07", "2023-10-07"),
                Contrat(3, "2022-12-21","2023-12-21")
            )
        }
    }
}
