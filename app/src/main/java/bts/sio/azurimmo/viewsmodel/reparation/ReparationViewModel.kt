package bts.sio.azurimmo.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import bts.sio.azurimmo.model.Reparation  // Mise à jour de l'import
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Type
import bts.sio.azurimmo.model.Societe

class ReparationViewModel : ViewModel() {
    private val _reparations = MutableStateFlow<List<Reparation>>(emptyList())
    val reparations: StateFlow<List<Reparation>> = _reparations

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init { getReparations() }

    fun getReparations() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val response = RetrofitInstance.api.getReparations()
                println("Données récupérées via API : $response") // Vérifie que les données arrivent ici
                if (response.isEmpty()) {
                    println("La liste de réparations est vide !")
                } else {
                    println("Nombre de réparations récupérées : ${response.size}")
                }
                _reparations.value = response
            } catch (e: Exception) {
                println("Erreur lors de l'appel API : ${e.message}")
                _errorMessage.value = "Erreur : ${e.message ?: "Problème de connexion"}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
